package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class UserMapper {

    UserDto toDto(User user) {
        return new UserDto(user.getId(),
                           user.getFirstName(),
                           user.getLastName(),
                           user.getBirthdate(),
                           user.getEmail());
    }

    UserDto toDto(Optional<User> optional_user) {
        if (optional_user.isPresent())
        {
            User user = optional_user.get();
            return new UserDto(user.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getBirthdate(),
                    user.getEmail());
        }
        else
        {
            return null;
        }

    }

    User toEntity(UserDto userDto) {
        return new User(
                        userDto.firstName(),
                        userDto.lastName(),
                        userDto.birthdate(),
                        userDto.email());
    }

}
