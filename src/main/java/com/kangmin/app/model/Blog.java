package com.kangmin.app.model;

import com.mongodb.lang.NonNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Builder
@Document(collection = "blogs")
public class Blog {

    @Id
    private String id;

    @Field
    @NonNull
    private String accountId;

    @Field
    @NonNull
    private String title;

    private String content;

    private long createdTimestamp;

    private Category category;

    private List<Tag> tags;

    // private List<String> comments;
}
