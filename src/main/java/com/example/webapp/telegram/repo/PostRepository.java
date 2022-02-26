package com.example.webapp.telegram.repo;

import com.example.webapp.telegram.entities.PostEntity;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<PostEntity,Long> {
}
