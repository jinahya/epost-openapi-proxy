package com.github.jinahya.epost.openapi.proxy.web.bind;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/dummy")
class DummyController {

    @GetMapping(path = "/empty")
    String readEmpty() {
        return "";
    }
}
