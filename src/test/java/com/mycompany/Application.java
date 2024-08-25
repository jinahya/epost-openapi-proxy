package com.mycompany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.support.WebStack;

@EnableHypermediaSupport(
        type = {
                EnableHypermediaSupport.HypermediaType.HAL
        },
        stacks = {
                WebStack.WEBFLUX
        }
)
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
