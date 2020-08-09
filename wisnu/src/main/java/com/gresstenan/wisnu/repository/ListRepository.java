package com.gresstenan.wisnu.repository;

import com.gresstenan.wisnu.models.ListItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ListRepository extends MongoRepository<ListItem, String> {
    Page<ListItem> findByPublished(boolean published, Pageable pageable);
    Page<ListItem> findByTitleContaining(String title, Pageable pageable);
}