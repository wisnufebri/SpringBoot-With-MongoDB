package com.gresstenan.wisnu.repository;

import com.gresstenan.wisnu.models.UserDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends MongoRepository<UserDetail, String> {
}