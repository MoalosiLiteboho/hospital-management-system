package com.geniescode.backend.dtoMappers;

import com.geniescode.backend.dto.UserCredentialsDTO;
import com.geniescode.backend.entities.User;

import java.util.function.Function;

public class UserCredentialDTOMapper implements Function<User, UserCredentialsDTO> {
    @Override
    public UserCredentialsDTO apply(User user) {
        return new UserCredentialsDTO(
                user.username(),
                user.Id());
    }
}
