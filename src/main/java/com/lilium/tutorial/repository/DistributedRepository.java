package com.lilium.tutorial.repository;

import com.lilium.tutorial.entity.DistributedEntity;
import org.springframework.data.repository.CrudRepository;

public interface DistributedRepository<ENTITY extends DistributedEntity> extends CrudRepository<ENTITY, Integer> {
}
