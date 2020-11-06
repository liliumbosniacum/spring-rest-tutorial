package com.lilium.tutorial.converter;

import com.lilium.tutorial.dto.UserDTO;
import com.lilium.tutorial.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserDTOConverter extends AbstractDTOConverter<User, UserDTO> {

    @Override
    public UserDTO convert(final User entity) {
        final UserDTO dto = new UserDTO();
        super.convert(entity, dto);

        dto.setName(entity.getName());
        dto.setUsername(entity.getUsername());

        return dto;
    }
}
