package com.gresstenan.wisnu.repository;

import com.gresstenan.wisnu.models.KurirModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KurirRepository extends MongoRepository<KurirModel, String> {
    
    Optional<KurirModel> findById(String s);
}
