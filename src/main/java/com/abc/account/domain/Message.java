package com.abc.account.domain;

import java.io.Serializable;

public class Message implements Serializable {
    private String value;

    public Message(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
