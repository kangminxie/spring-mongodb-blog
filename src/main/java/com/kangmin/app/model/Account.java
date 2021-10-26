package com.kangmin.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.lang.NonNull;
import lombok.Builder;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Builder
@Setter
@Document(collection = "accounts")
public class Account {

    @Id
    private String id;

    @Field
    @NonNull
    @JsonProperty
    private String username;

    @Field
    @NonNull
    @JsonProperty
    private String displayName;

    @Field
    @NonNull
    @JsonProperty
    private String email;

    @Field
    @NonNull
    @JsonProperty
    private String password;

    public Account() {

    }

    public Account(
        final String id,
        final String username,
        final String displayName,
        final String email,
        final String password
    ) {
        this.id = id;
        this.username = username;
        this.displayName = displayName;
        this.email = email;
        this.password = password;
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
            "Account[id=%s, username='%s', email='%s', displayName='%s']",
            id, username, email, displayName);
    }
}
