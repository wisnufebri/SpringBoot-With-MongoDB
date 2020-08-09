package com.gresstenan.wisnu.repository;

import com.gresstenan.wisnu.models.ItemModel;
import com.gresstenan.wisnu.models.Tutorial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends MongoRepository<ItemModel, String> {
    Optional<ItemModel> findById(String id);
    Page<ItemModel> findBynama(String nama, Pageable pageable);
}