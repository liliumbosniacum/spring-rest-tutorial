package com.lilium.tutorial.service;

import com.lilium.tutorial.api.UserApi;
import com.lilium.tutorial.converter.UserDTOConverter;
import com.lilium.tutorial.dto.UserDTO;
import com.lilium.tutorial.entity.User;
import com.lilium.tutorial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractCRUDLService<User, UserDTO> implements UserApi {
    private UserRepository userRepository;
    private UserDTOConverter userDTOConverter;

    @Autowired
    public UserService(final UserRepository userRepository, final UserDTOConverter userDTOConverter) {
        super(userRepository, userDTOConverter);
        this.userRepository = userRepository;
        this.userDTOConverter = userDTOConverter;
    }

    @Override
    protected void updateEntity(User entity, UserDTO dto) {
        entity.setName(dto.getName());
        entity.setUsername(dto.getUsername());
    }
}
