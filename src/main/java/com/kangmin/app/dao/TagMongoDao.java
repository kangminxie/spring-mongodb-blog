package com.kangmin.app.dao;

import com.kangmin.app.model.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TagMongoDao extends MongoRepository<Tag, String> {

}
