package com.lilium.tutorial.service;

import com.lilium.tutorial.api.ObjectTypeApi;
import com.lilium.tutorial.converter.ObjectTypeDTOConverter;
import com.lilium.tutorial.dto.ObjectTypeDTO;
import com.lilium.tutorial.entity.ObjectType;
import com.lilium.tutorial.repository.ObjectTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObjectTypeService extends AbstractCRUDLService<ObjectType, ObjectTypeDTO> implements ObjectTypeApi {

    @Autowired
    public ObjectTypeService(final ObjectTypeRepository repository,
                             final ObjectTypeDTOConverter converter) {
        super(repository, converter);
    }

    @Override
    protected void updateEntity(ObjectType entity, ObjectTypeDTO dto) {
        entity.setName(dto.getName());
    }
}
