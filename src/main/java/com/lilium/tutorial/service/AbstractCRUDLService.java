package com.lilium.tutorial.service;

import com.google.common.collect.Lists;
import com.lilium.tutorial.api.AbstractCRUDLApi;
import com.lilium.tutorial.converter.AbstractDTOConverter;
import com.lilium.tutorial.dto.BaseDTO;
import com.lilium.tutorial.entity.DistributedEntity;
import com.lilium.tutorial.repository.DistributedRepository;
import com.lilium.tutorial.repository.DistributedRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.GenericTypeResolver;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public abstract class AbstractCRUDLService<ENTITY extends DistributedEntity, DTO extends BaseDTO>
        implements AbstractCRUDLApi<ENTITY, DTO> {
    // region Member
    private static final Logger LOG = LoggerFactory.getLogger(AbstractCRUDLService.class);
    private DistributedRepository<ENTITY> repository;
    private AbstractDTOConverter<ENTITY, DTO> converter;
    private Class<ENTITY> entityClass;
    // endregion

    // region Constructor
    public AbstractCRUDLService(final DistributedRepository<ENTITY> repository,
                                final AbstractDTOConverter<ENTITY, DTO> converter) {
        this.repository = repository;
        this.converter = converter;

        final Class<?>[] params = GenericTypeResolver.resolveTypeArguments(getClass(), AbstractCRUDLService.class);
        entityClass = (Class<ENTITY>) params[0];
    }
    // endregion

    // region Implementation
    @Override
    public DTO save(DTO dto) {
        final ENTITY entity;
        if (dto.isNew()) { // Save for the first time
            entity = initEntity();
        } else { // Update existing entity
            entity = findEntityById(dto.getId());
        }

        if (entity == null) {
            LOG.error("Failed to save the entity of class '{}'", entityClass.getSimpleName());
            return null;
        }

        // Set modified timestamp
        entity.setModified(LocalDateTime.now());

        // Map dto to entity
        updateEntity(entity, dto);

        // Save entity to DB
        final ENTITY savedEntity = repository.save(entity);

        // Convert saved entity and return DTO
        return converter.convert(savedEntity);
    }

    @Override
    public DTO getById(Integer id) {
        final ENTITY entity = findEntityById(id);

        if (entity == null) {
            LOG.error("Failed to find entity with ID '{}'", id);
            return null;
        }

        return converter.convert(entity);
    }

    @Override
    public List<DTO> list() {
        final List<ENTITY> entities = Lists.newArrayList(repository.findAll());

        return getDtos(entities);
    }

    @Override
    public List<DTO> modifiedSince(LocalDateTime time) {
        final List<ENTITY> entities = repository.findAllModifiedSince(time);

        return getDtos(entities);
    }

    @Override
    public Boolean delete(Integer id) {
        final ENTITY entity = findEntityById(id);
        if (entity == null) {
            LOG.error("Failed to delete entity with ID '{}' as it does not exist", id);
            return false;
        }

        try {
            repository.delete(entity);
            return true;
        } catch (final Exception e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
    }

    protected abstract void updateEntity(final ENTITY entity, final DTO dto);
    // endregion

    // region Helper
    private ENTITY initEntity() {
        try {
            final ENTITY entity = entityClass.newInstance();
            entity.setCreated(LocalDateTime.now());

            return entity;
        } catch (final InstantiationException | IllegalAccessException e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    private ENTITY findEntityById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    private List<DTO> getDtos(List<ENTITY> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return Collections.emptyList();
        }

        return converter.convertList(entities);
    }
    // endregion
}
