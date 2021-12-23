package com.kangmin.app.service;

import com.kangmin.app.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BlogService {

    Page<Blog> findPagedWithQuery(String query, Pageable pageable);

    Optional<Blog> findById(String blogId);

    List<Blog> findByAccountId(String accountId);

    Blog save(Blog blog);

    // == for admin ==
    List<Blog> findAllByQuery(String query);
}
