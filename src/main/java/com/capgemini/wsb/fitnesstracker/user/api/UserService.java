package com.capgemini.wsb.fitnesstracker.user.api;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
@Service
public interface UserService {

    /**
     * Creates a new user in the system based on provided user data.
     *
     * @param newUserDto user data for creation
     * @return UserDto representing the created user
     */
    UserDto createUser(UserDto newUserDto);

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete
     * @return true if the user was successfully deleted, false otherwise
     */
    boolean deleteUser(Long id);

    /**
     * Updates an existing user's information based on the provided data.
     *
     * @param id the ID of the user to update.
     * @param newUserDto new user data to update.
     * @return an Optional containing the updated UserDto if the update was successful, otherwise an empty Optional if the user was not found.
     */
    Optional<UserDto> updateUser(Long id, UserDto newUserDto);

    /**
     * Finds all users who are older than a specified date.
     *
     * @param time the date to compare users' birthdate against.
     * @return a list of UserDto representing users who are older than the specified date.
     */
    List<UserDto> findUsersOlderThan(LocalDate time);

}
