package com.kangmin.app.service.impl;

import com.kangmin.app.dao.BlogMongoDao;
import com.kangmin.app.model.Blog;
import com.kangmin.app.service.BlogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {

    private final BlogMongoDao blogMongoDao;

    public BlogServiceImpl(final BlogMongoDao blogMongoDao) {
        this.blogMongoDao = blogMongoDao;
    }

    @Override
    public Page<Blog> findPagedWithQuery(final String query, final Pageable pageable) {
        return blogMongoDao.findByContentOrTitleOrCategoryQuery(query, pageable);
    }

    @Override
    public Optional<Blog> findById(final String blogId) {
        return blogMongoDao.findById(blogId);
    }

    @Override
    public List<Blog> findByAccountId(final String accountId) {
        return blogMongoDao.findByAccountId(accountId);
    }

    @Override
    public Blog save(final Blog blog) {
        return blogMongoDao.save(blog);
    }

    @Override
    public List<Blog> findAllByQuery(final String query) {
        if (query == null || query.isEmpty()) {
            return blogMongoDao.findAll();
        }
        return blogMongoDao.findByContentOrTitleOrCategoryQuery(query);
    }
}
