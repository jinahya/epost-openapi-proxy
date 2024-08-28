package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.download_area_code_service;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.IgnoredErrorType;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import java.util.zip.ZipFile;

@Disabled("takes too long")
@Slf4j
class AreaCodeInfoExcelTest {

    private static Stream<File> getResourceFileStream() {
        return AreaCodeInfoUtilsTest.getResourceFileStream();
    }

    // https://poi.apache.org/components/spreadsheet/how-to.html#sxssf
    @MethodSource({
            "getResourceFileStream"
    })
    @ParameterizedTest
    void __(final File source) throws IOException {
        System.setProperty("java.awt.headless", "true");
        try (var workbook = new SXSSFWorkbook(1);
             var zipFile = new ZipFile(source, AreaCodeInfoUtils.CHARSET)) {
//            workbook.setCompressTempFiles(true);
            for (var e = zipFile.entries(); e.hasMoreElements(); ) {
                final var entry = e.nextElement();
                if (entry.isDirectory() || !entry.getName().endsWith(".txt")) {
                    continue;
                }
                var name = entry.getName();
                name = name.substring(name.lastIndexOf('/') + 1, name.length() - 4);
                log.debug("name: {}", name);
                final var sheet = workbook.createSheet(name);
                final var colcnt = new AtomicInteger();
                final var rownum = new AtomicInteger();
                AreaCodeInfoUtils.extract(
                        zipFile.getInputStream(entry),
                        h -> {
                            colcnt.set(h.length);
                            final var row = sheet.createRow(rownum.getAndIncrement());
                            for (int i = 0; i < h.length; i++) {
                                final var cell = row.createCell(i);
                                cell.setCellType(CellType.STRING);
                                cell.setCellValue(h[i]);
                            }
                            sheet.createFreezePane(0, 1);
                        },
                        r -> {
                            final var row = sheet.createRow(rownum.getAndIncrement());
                            for (int i = 0; i < r.length; i++) {
                                if (r[i].isBlank()) {
                                    continue;
                                }
                                final var cell = row.createCell(i);
                                cell.setCellType(CellType.STRING);
                                cell.setCellValue(r[i]);
                            }
                        }
                );
                // https://stackoverflow.com/a/47478314/330457
                try {
                    final var _sh = SXSSFSheet.class.getDeclaredField("_sh");
                    _sh.setAccessible(true);
                    final var xssfsheet = (XSSFSheet) _sh.get(sheet);
                    xssfsheet.addIgnoredErrors(new CellRangeAddress(1, rownum.get(), 0, colcnt.get()),
                                               IgnoredErrorType.NUMBER_STORED_AS_TEXT);
                } catch (final ReflectiveOperationException roe) {
                    log.error("failed to set IgnoredErrorType.NUMBER_STORED_AS_TEXT", roe);
                }
            }
            final File target = new File(source.getParent(), source.getName() + ".xlsx");
            try (var output = new FileOutputStream(target)) {
                workbook.write(output);
            }
        }
    }
}
