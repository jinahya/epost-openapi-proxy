package com.github.jinahya.epost.openapi.proxy.web.bind.download_area_code_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.download_area_code_service.AreaCodeInfoResponse;
import com.github.jinahya.epost.openapi.proxy.web.bind.AbstractWrappingModel2Test;

class AreaCodeInfo2Test
        extends AbstractWrappingModel2Test<AreaCodeInfo2, AreaCodeInfoResponse> {

    AreaCodeInfo2Test() {
        super(AreaCodeInfo2.class, AreaCodeInfoResponse.class);
    }
}