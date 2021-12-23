package com.kangmin.app.config.oauth2.model;

public enum AuthProvider {

    GOOGLE("GOOGLE"),
    FACEBOOK("FACEBOOK"),
    GITHUB("GITHUB"),
    LOCAL("LOCAL");

    private final String name;

    AuthProvider(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
