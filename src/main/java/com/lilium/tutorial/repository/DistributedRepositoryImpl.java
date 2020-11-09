package com.lilium.tutorial.repository;

import com.lilium.tutorial.entity.DistributedEntity_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

public class DistributedRepositoryImpl<ENTITY> extends SimpleJpaRepository<ENTITY, Integer> implements DistributedRepository<ENTITY>, JpaSpecificationExecutor<ENTITY> {

    public DistributedRepositoryImpl(final JpaEntityInformation<ENTITY, Integer> entityInformation,
                                     final EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    @Override
    public List<ENTITY> findAllModifiedSince(final LocalDateTime time) {
        return super.findAll(modifiedSince(time));
    }

    private Specification<ENTITY> modifiedSince(final LocalDateTime time) {
        return ((root, query, cb) -> cb.greaterThanOrEqualTo(root.get(DistributedEntity_.MODIFIED), time));
    }
}
