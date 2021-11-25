package com.kangmin.app.controller.rest;

import com.kangmin.app.dao.CategoryMongoDao;
import com.kangmin.app.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryMongoDao categoryMongoDao;

    @Autowired
    public CategoryController(final CategoryMongoDao categoryMongoDao) {
        this.categoryMongoDao = categoryMongoDao;
    }

    // == Category CRUD ==

    @RequestMapping({"", "all"})
    public List<Category> showAllCategories() {
        return categoryMongoDao.findAll();
    }

    @RequestMapping("{id}")
    public Category showSingleCategory(@PathVariable final String id) {
        return categoryMongoDao.findById(id).orElse(null);
    }
}
