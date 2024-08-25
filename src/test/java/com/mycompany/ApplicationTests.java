package com.mycompany;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.boot.test.context.SpringBootTest;

@EnabledIfEnvironmentVariable(named = "SERVICE_KEY", matches = ".+")
@SpringBootTest
class ApplicationTests {

    @Test
    void contextLoads() {
    }
}
