package com.lilium.tutorial;

import com.lilium.tutorial.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class UserRestTest {
    private static final RestTemplate REST_TEMPLATE = new RestTemplate();
    private static final String URL = "http://localhost:8080/api/users";

    @Test
    public void testUserCRUDL() {
        final UserDTO dto = new UserDTO();
        dto.setUsername("Username " + UUID.randomUUID());
        dto.setName("Name " + UUID.randomUUID());

        final UserDTO savedDTO = REST_TEMPLATE.postForObject(URL, dto, UserDTO.class);
        verifyUserDTO(dto, savedDTO);

        savedDTO.setName("Update name");
        final UserDTO updatedDTO = REST_TEMPLATE.postForObject(URL, savedDTO, UserDTO.class);
        verifyUserDTO(savedDTO, updatedDTO);

        final UserDTO byId = REST_TEMPLATE.getForObject(URL + "/" + savedDTO.getId(), UserDTO.class);
        verifyUserDTO(savedDTO, byId);

        final List dtos = REST_TEMPLATE.getForObject(URL + "/list", List.class);
        assertThat(dtos).isNotEmpty();

        REST_TEMPLATE.delete(URL + "/" + savedDTO.getId());
        final UserDTO deleted = REST_TEMPLATE.getForObject(URL + "/" + savedDTO.getId(), UserDTO.class);
        assertThat(deleted).isNull();
    }

    private void verifyUserDTO(UserDTO expected, UserDTO actual) {
        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isNotNull();
        assertThat(actual.getCreated()).isNotNull();
        assertThat(actual.getModified()).isNotNull();
        assertThat(actual.getUsername()).isEqualTo(expected.getUsername());
        assertThat(actual.getName()).isEqualTo(expected.getName());
    }
}
