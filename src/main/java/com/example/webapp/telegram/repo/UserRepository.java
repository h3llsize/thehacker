package com.example.webapp.telegram.repo;

import com.example.webapp.telegram.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
