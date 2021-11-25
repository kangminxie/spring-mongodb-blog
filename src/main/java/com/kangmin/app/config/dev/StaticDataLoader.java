package com.kangmin.app.config.dev;

import com.kangmin.app.dao.AccountMongoDao;
import com.kangmin.app.dao.BlogMongoDao;
import com.kangmin.app.dao.CategoryMongoDao;
import com.kangmin.app.dao.TagMongoDao;
import com.kangmin.app.model.Account;
import com.kangmin.app.model.Blog;
import com.kangmin.app.model.Category;
import com.kangmin.app.model.Tag;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class StaticDataLoader implements CommandLineRunner {

    private final AccountMongoDao accountMongoDao;
    private final BlogMongoDao blogMongoDao;
    private final CategoryMongoDao categoryMongoDao;
    private final TagMongoDao tagMongoDao;

    public StaticDataLoader(
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

    private void createDevAccounts() {
        if (accountMongoDao.findAll().isEmpty()) {
            final Account sa = new Account(
                "id-0000",
                "sa",
                "Super Admin1",
                "sa@test.com",
                "password"
            );
            final Account dev = Account.builder()
                .id("id-0001")
                .username("dev")
                .password("password")
                .email("dev@test.com")
                .displayName("Developer Admin1")
                .build();
            final Account normal = Account.builder()
                .id("id-0002")
                .username("normal")
                .password("password")
                .email("normal@test.com")
                .displayName("Normal User1")
                .build();
            accountMongoDao.saveAll(Arrays.asList(sa, dev, normal));
        }
    }


    @Override
    public void run(final String... args) {
        createDevAccounts();

        // fetch all accounts
        System.out.println("Accounts found with findAll():");
        System.out.println("-------------------------------");
        for (final Account account : accountMongoDao.findAll()) {
            System.out.println(account);
        }

        // fetch an individual account
        System.out.println("\nAccount found with findById('id-0000'):");
        System.out.println("--------------------------------");
        System.out.println(accountMongoDao.findById("id-0000")
            .orElseThrow(() -> new RuntimeException("no account associated with id-0000")));
        System.out.println("\nAccounts found with findByDisplayName('Developer Admin'):");
        System.out.println("--------------------------------");
        for (Account account : accountMongoDao.findByDisplayName("Developer Admin1")) {
            System.out.println(account);
        }

        System.out.println("Done with account test:");

        System.out.println("Start blog with account test:");

        if (categoryMongoDao.findAll().isEmpty()) {
            final Category c1 = new Category();
            c1.setId("cid-001");
            c1.setName("CategoryName1");
            categoryMongoDao.save(c1);
        }
        System.out.println("Categories found with findAll():");
        System.out.println("-------------------------------");
        final List<Category> categories = categoryMongoDao.findAll();
        for (final Category category : categories) {
            System.out.println(category);
        }

        if (tagMongoDao.findAll().isEmpty()) {
            final Tag t1d = new Tag();
            t1d.setId("tid-001");
            t1d.setName("tagName1");
            tagMongoDao.save(t1d);
            final Tag t2d = new Tag();
            t2d.setId("tid-002");
            t2d.setName("tagName2");
            tagMongoDao.save(t2d);
        }
        System.out.println("Tags found with findAll():");
        System.out.println("-------------------------------");
        final List<Tag> tags = tagMongoDao.findAll();
        for (final Tag tag : tags) {
            System.out.println(tag);
        }

        if (blogMongoDao.findAll().isEmpty()) {
            final Category c1 = categories.get(0);
            final Tag t1 = tags.get(0);
            final Tag t2 = tags.get(1);
            final Blog blog = Blog.builder()
                .id("blog-id-0001")
                .accountId("id-0000")
                .category(c1)
                .tags(Arrays.asList(t1, t2))
                .content("I am testing blog")
                .createdTimestamp(System.currentTimeMillis())
                .build();
            blogMongoDao.save(blog);
        }
        System.out.println("Blogs found with findAll():");
        System.out.println("-------------------------------");
        final List<Blog> blogs = blogMongoDao.findAll();
        for (final Blog b : blogs) {
            System.out.println(b);
        }
    }
}
