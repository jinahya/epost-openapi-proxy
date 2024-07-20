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
    requires spring.web;
    requires org.slf4j;
    requires spring.core;
    requires java.desktop;
    requires spring.beans;
    requires spring.context;

    exports com.github.jinahya.epost.openapi.proxy._common;
    exports com.github.jinahya.epost.openapi.proxy.download_area_code_service;
    exports com.github.jinahya.epost.openapi.proxy.retrieve_lot_number_adress_area_cd_service;
    exports com.github.jinahya.epost.openapi.proxy.retrieve_new_adress_area_cd_search_all_service;
    exports com.github.jinahya.epost.openapi.proxy.retrieve_new_adress_area_cd_service;
    exports com.github.jinahya.epost.openapi.proxy.retrieve_eng_address_service;
}
