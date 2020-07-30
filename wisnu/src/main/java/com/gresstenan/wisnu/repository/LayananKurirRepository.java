package com.gresstenan.wisnu.repository;


import com.gresstenan.wisnu.models.LayananKurirModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface LayananKurirRepository extends MongoRepository<LayananKurirModel, String> {

    Optional<LayananKurirModel> findById(String id);

    @Query("{'kurirmodel.id': ?0}")
    List<LayananKurirModel> findByIdkurir(String id);
}
