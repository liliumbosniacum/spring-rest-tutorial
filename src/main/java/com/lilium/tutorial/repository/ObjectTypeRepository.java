package com.lilium.tutorial.repository;

import com.lilium.tutorial.entity.ObjectType;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjectTypeRepository extends DistributedRepository<ObjectType> {
}
