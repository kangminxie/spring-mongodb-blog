package com.kangmin.app.model.security;

public enum WebUserPermission {

    COURSE_READ("course:read"),
    COURSE_WRITE("course:write"),

    ACCOUNT_READ("account:read"),
    ACCOUNT_WRITE("account:write"),

    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write");

    private final String name;

    WebUserPermission(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
