package com.kangmin.app.config.oauth2;

import com.kangmin.app.config.oauth2.model.AuthProvider;
import com.kangmin.app.config.oauth2.model.FacebookOAuth2UserInfo;
import com.kangmin.app.config.oauth2.model.GithubOAuth2UserInfo;
import com.kangmin.app.config.oauth2.model.GoogleOAuth2UserInfo;
import com.kangmin.app.config.oauth2.model.OAuth2UserInfo;
import com.kangmin.app.config.oauth2.exception.OAuth2AuthenticationProcessingException;

import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(
        final String registrationId,
        final Map<String, Object> attributes
    ) {
        if (registrationId.equalsIgnoreCase(AuthProvider.GOOGLE.name())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.FACEBOOK.name())) {
            return new FacebookOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.GITHUB.name())) {
            return new GithubOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException(
                    "Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}
