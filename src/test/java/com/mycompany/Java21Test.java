package com.mycompany;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class Java21Test {

    @Test
    void __() {
        assertThatThrownBy(() -> new ArrayList<Void>().getLast())
                .isInstanceOf(NoSuchElementException.class);
    }
}
