package com.venu;

import org.springframework.stereotype.Component;

public class Bean1 implements  FunctionalInt{

    private String field1;

    @Override
    public String intMethod(String input) {
        return "test";
    }
}
