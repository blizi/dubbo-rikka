package com.rikka.common.constant;

public enum SecretConstant {
    SECRET("ScrEt");
    private String name;

    public String getName() {
        return name;
    }

    SecretConstant(String scrEt) {
        this.name = scrEt;
    }
}
