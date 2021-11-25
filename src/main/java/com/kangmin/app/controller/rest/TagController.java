package com.kangmin.app.controller.rest;

import com.kangmin.app.dao.TagMongoDao;
import com.kangmin.app.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tags")
public class TagController {

    private final TagMongoDao tagMongoDao;

    @Autowired
    public TagController(final TagMongoDao tagMongoDao) {
        this.tagMongoDao = tagMongoDao;
    }

    // == Tag CRUD ==

    @RequestMapping({"", "all"})
    public List<Tag> showAllTags() {
        return tagMongoDao.findAll();
    }

    @RequestMapping("{id}")
    public Tag showSingleTag(@PathVariable final String id) {
        return tagMongoDao.findById(id).orElse(null);
    }
}
