package com.kangmin.app.dao;

import com.kangmin.app.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryMongoDao extends MongoRepository<Category, String> {

}
