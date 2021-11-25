package com.kangmin.app.model.payload;

import lombok.Data;

import java.util.List;

@Data
public class CreateBlogRequest {

    private String title;

    private String content;

    private String categoryId;

    private List<String> tagIds;
}
