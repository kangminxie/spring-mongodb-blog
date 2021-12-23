package com.kangmin.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kangmin.app.config.oauth2.model.AuthProvider;
import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Builder
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "accounts")
public class Account {

    @Id
    private String id;

    @Field
    @NonNull
    @JsonProperty
    @Indexed(unique = true)
    private String username;

    @Field
    @NonNull
    @JsonProperty
    private String displayName;

    @Field
    @NonNull
    @JsonProperty
    @Indexed(unique = true)
    private String email;

    @Field
    @NonNull
    @JsonProperty
    private String role;

    @Field
    @NonNull
    @JsonProperty
    private String password;

    @Field
    @JsonProperty
    private String providerId;

    @Field
    @JsonProperty
    private AuthProvider provider;

    @Field
    @NonNull
    @JsonProperty
    private Long createdTimestamp;

    public Account() {

    }

    public Account(
        final String id,
        final String username,
        final String displayName,
        final String email,
        final String password,
        final String role,
        final String providerId
    ) {
        this.id = id;
        this.username = username;
        this.displayName = displayName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.createdTimestamp = System.currentTimeMillis();
        this.providerId = providerId;
        this.provider = AuthProvider.LOCAL;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return String.format(
            "Account[id=%s, username='%s', email='%s', displayName='%s', role='%s']",
            id, username, email, displayName, role);
    }
}
