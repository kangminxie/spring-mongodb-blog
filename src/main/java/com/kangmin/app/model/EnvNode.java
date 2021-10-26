package com.kangmin.app.model;

public enum EnvNode {

    DEV("DEV"),
    STAGE("STAGE"),
    PROD("PROD");

    private final String name;

    EnvNode(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
