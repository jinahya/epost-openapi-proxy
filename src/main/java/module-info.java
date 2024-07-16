module com.github.jinahya.epost.openapi.proxy {

    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires jakarta.validation;
    requires jakarta.xml.bind;
    requires java.xml;
    requires static lombok;
    requires spring.boot.autoconfigure;
    requires spring.boot;

    exports com.github.jinahya.epost.openapi.proxy.common;
    exports com.github.jinahya.epost.openapi.proxy.retrieve_new_adress_area_cd_search_all_service;
    exports com.github.jinahya.epost.openapi.proxy.retrieve_new_adress_area_cd_service;
}
