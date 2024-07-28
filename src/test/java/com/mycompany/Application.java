package com.mycompany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@ConfigurationPropertiesScan(
//        basePackageClasses = {
////                com.mycompany.Application.class,
////                com.github.jinahya.epost.openapi.proxy.NoOp.class
//        }
//)
@ComponentScan(
        basePackageClasses = {
                com.mycompany.Application.class,
                com.github.jinahya.epost.openapi.proxy.NoOp.class
        }
)
@SpringBootApplication
public class Application {

    public static void main(final String... args) {
        SpringApplication.run(Application.class, args);
    }
}
