package com.kangmin.app.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "tags")
public class Tag {

    @Id
    private String id;

    @Indexed
    private String name;
}
