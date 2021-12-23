package com.kangmin.app.config.oauth2.model;

import java.util.Map;

public abstract class OAuth2UserInfo {

    private final Map<String, Object> attributes;

    public OAuth2UserInfo(final Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public abstract String getId();

    public abstract String getName();

    public abstract String getEmail();

    public abstract String getImageUrl();
}
