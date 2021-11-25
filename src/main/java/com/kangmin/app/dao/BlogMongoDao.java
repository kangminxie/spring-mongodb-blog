package com.kangmin.app.dao;

import com.kangmin.app.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BlogMongoDao extends MongoRepository<Blog, String> {

    // Optional<Blog> findById(String id);

    List<Blog> findByAccountId(String accountId);

    // Page<Blog> findAll(Pageable pageable);

    // Iterable<T> findAll(Sort sort);

    // MongoRepository<T, ID> extends PagingAndSortingRepository<T, ID>, QueryByExampleExecutor<T>
    // PagingAndSortingRepository<T, ID> extends CrudRepository<T, ID>
    //            methods provided by CrudRepository<T, ID>:
    //            T = Blog
    //            ID = Long
    //
    //            CREATE:
    //                <S extends T> S save(S entity);
    //
    //            READ:
    //                Optional<T> findById(ID id);
    //                boolean existsById(ID id);
    //                long count();
    //                T getOne(ID id);
    //
    //            UPDATE:
    //                <S extends T> S save(S entity);
    //
    //            DELETE:
    //                void deleteById(ID id);
    //                void delete(T entity);
    //
    //            PagingAndSortingRepository:
    //  Page<T> findAll(Pageable pageable);
    //  Iterable<T> findAll(Sort sort);

    @Query("{'content': {'$regex': ?0 , $options: 'i'}}")
    List<Blog> findByContentQuery(String query);

    @Query("{'content': {'$regex': ?0 , $options: 'i'}}")
    Page<Blog> findByContentQuery(String query, Pageable pageable);

    @Query("{ $or: [{'title': {'$regex': ?0 , $options: 'i'}}, {'content': {'$regex': ?0 , $options: 'i'}}]}")
    List<Blog> findByContentOrTitleOrCategoryQuery(String query);

    @Query("{ $or: ["
        + "{'title': {'$regex': ?0 , $options: 'i'}},"
        + "{'content': {'$regex': ?0 , $options: 'i'}},"
        + "{'category.name': {'$regex': ?0 , $options: 'i'}}"
        + "]}")
    Page<Blog> findByContentOrTitleOrCategoryQuery(String query, Pageable pageable);
}
