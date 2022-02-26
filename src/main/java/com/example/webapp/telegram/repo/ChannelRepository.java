package com.example.webapp.telegram.repo;

import com.example.webapp.telegram.entities.ChannelEntity;
import org.springframework.data.repository.CrudRepository;

public interface ChannelRepository extends CrudRepository<ChannelEntity, Long> {
}
