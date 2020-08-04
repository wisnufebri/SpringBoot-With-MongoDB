package com.gresstenan.wisnu.repository;

import com.gresstenan.wisnu.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findById(String id);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query("{'userDetail.alamat': { $regex: ?0 } }")
    List<User> findByAlamat(String alamat);
}