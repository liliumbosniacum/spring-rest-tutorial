package com.lilium.tutorial.converter;

import com.lilium.tutorial.dto.ObjectTypeDTO;
import com.lilium.tutorial.entity.ObjectType;
import org.springframework.stereotype.Component;

@Component
public class ObjectTypeDTOConverter extends AbstractDTOConverter<ObjectType, ObjectTypeDTO> {

    @Override
    public ObjectTypeDTO convert(ObjectType entity) {
        final ObjectTypeDTO dto = new ObjectTypeDTO();
        super.convert(entity, dto);
        dto.setName(entity.getName());

        return dto;
    }
}
