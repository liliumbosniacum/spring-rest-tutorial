package com.lilium.tutorial.controller;

import com.lilium.tutorial.api.UserApi;
import com.lilium.tutorial.dto.UserDTO;
import com.lilium.tutorial.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController extends AbstractCRUDLController<User, UserDTO> {
    private UserApi api;

    @Autowired
    public UserController(final UserApi api) {
        super(api);
    }
}
