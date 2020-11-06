package com.lilium.tutorial.repository;

import com.lilium.tutorial.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends DistributedRepository<User> {
}
