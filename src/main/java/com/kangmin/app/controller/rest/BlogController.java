package com.kangmin.app.controller.rest;

import com.kangmin.app.dao.AccountMongoDao;
import com.kangmin.app.dao.BlogMongoDao;
import com.kangmin.app.dao.CategoryMongoDao;
import com.kangmin.app.dao.TagMongoDao;
import com.kangmin.app.model.Account;
import com.kangmin.app.model.Blog;
import com.kangmin.app.model.Category;
import com.kangmin.app.model.Tag;
import com.kangmin.app.model.payload.CreateBlogRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/blogs")
public class BlogController {
    private static final Logger LOG = LoggerFactory.getLogger(BlogController.class);
    private static final int DEFAULT_HOME_PAGE_SIZE = 2;

    private final AccountMongoDao accountMongoDao;
    private final BlogMongoDao blogMongoDao;
    private final CategoryMongoDao categoryMongoDao;
    private final TagMongoDao tagMongoDao;

    @Autowired
    public BlogController(
        final AccountMongoDao accountMongoDao,
        final BlogMongoDao blogMongoDao,
        final CategoryMongoDao categoryMongoDao,
        final TagMongoDao tagMongoDao
    ) {
        this.accountMongoDao = accountMongoDao;
        this.blogMongoDao = blogMongoDao;
        this.categoryMongoDao = categoryMongoDao;
        this.tagMongoDao = tagMongoDao;
    }

    // == Blog ==

    // http://localhost:8080/api/v1/blogs?query=abcd&sort=id,asc
    @RequestMapping({""})
    public Page<Blog> showBlogsByQuery(
        final @PageableDefault(
            size = DEFAULT_HOME_PAGE_SIZE,
            sort = {"id"},
            direction = Sort.Direction.DESC
        ) Pageable pageable,
        final @RequestParam(value = "query", required = false) String query
    ) {
        LOG.debug("BlogController.topBlogsPage is visited with query={} and pageable={}", query, pageable);
        return blogMongoDao.findByContentOrTitleOrCategoryQuery(query, pageable);
    }

    // == admin purpose ==
    @RequestMapping({"/all"})
    public List<Blog> showAllBlogs(
        final @RequestParam(value = "query", required = false) String query
    ) {
        if (query == null || query.isEmpty()) {
            return blogMongoDao.findAll();
        }
        return blogMongoDao.findByContentOrTitleOrCategoryQuery(query);
    }

    @PostMapping("")
    public Blog createOneBlog(final @RequestBody CreateBlogRequest request) {
        final String username = "sa";
        final Account account = accountMongoDao.findByUsername(username).orElse(null);
        final String accountId = account == null ? "id-0000" : account.getId();
        final Category category = categoryMongoDao.findById(request.getCategoryId()).orElse(null);
        final List<Tag> tags = new ArrayList<>();
        tagMongoDao.findAllById(request.getTagIds()).forEach(tags::add);
        final Blog blog = Blog.builder()
            .id(UUID.randomUUID().toString())
            .accountId(accountId)
            .title(request.getTitle())
            .content(request.getContent())
            .createdTimestamp(System.currentTimeMillis())
            .category(category)
            .tags(tags)
            .build();
        return blogMongoDao.save(blog);
    }

    @RequestMapping("/{id}")
    public Blog showSingleBlog(@PathVariable final String id) {
        return blogMongoDao.findById(id).orElse(null);
    }

    @RequestMapping("/account/{accountId}")
    public List<Blog> showBlogsForAccount(@PathVariable final String accountId) {
        return blogMongoDao.findByAccountId(accountId);
    }
}
