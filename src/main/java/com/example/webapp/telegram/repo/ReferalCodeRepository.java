package com.example.webapp.telegram.repo;

import com.example.webapp.telegram.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface ReferalCodeRepository extends CrudRepository<UserEntity, String> {

}
