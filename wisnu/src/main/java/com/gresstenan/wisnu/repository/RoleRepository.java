package com.gresstenan.wisnu.repository;

import com.gresstenan.wisnu.models.ERole;
import com.gresstenan.wisnu.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
