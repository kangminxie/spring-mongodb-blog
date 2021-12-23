package com.kangmin.app.dao;

import com.kangmin.app.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountMongoDao extends MongoRepository<Account, String> {

    // Optional<Account> findById(String id);

    Optional<Account> findByUsername(@Param("username") String username);

    Optional<Account> findByEmail(@Param("email") String email);

    List<Account> findByDisplayName(@Param("displayName") String displayName);

    boolean existsByUsername(final String username);

    boolean existsByEmail(final String email);

    // Page<Account> findAll(Pageable pageable);

    // Iterable<T> findAll(Sort sort);


    // MongoRepository<T, ID> extends PagingAndSortingRepository<T, ID>, QueryByExampleExecutor<T>
    // PagingAndSortingRepository<T, ID> extends CrudRepository<T, ID>
    //            methods provided by CrudRepository<T, ID>:
    //            T = Account
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
}
