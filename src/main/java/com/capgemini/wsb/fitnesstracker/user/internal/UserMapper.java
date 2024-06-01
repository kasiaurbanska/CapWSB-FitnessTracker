package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;

import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import org.springframework.stereotype.Component;

/**
 * Component responsible for mapping between {@link User} entities and {@link UserDto} data transfer objects.
 * This mapper facilitates the conversion of user data between the database entity form and the form used for data transfer in API interactions.
 */
@Component
public class UserMapper {

    /**
     * Converts a {@link User} entity to a {@link UserDto}.
     *
     * @param user the user entity to convert
     * @return the corresponding user DTO
     */
    UserDto toDto(User user) {
        return new UserDto(user.getId(),
                           user.getFirstName(),
                           user.getLastName(),
                           user.getBirthdate(),
                           user.getEmail());
    }

    /**
     * Converts a {@link UserDto} to a {@link User} entity.
     *
     * @param userDto the user DTO to convert
     * @return the corresponding user entity
     */
    public User toEntity(UserDto userDto) {
        return new User(
                        userDto.firstName(),
                        userDto.lastName(),
                        userDto.birthdate(),
                        userDto.email());
    }

}
