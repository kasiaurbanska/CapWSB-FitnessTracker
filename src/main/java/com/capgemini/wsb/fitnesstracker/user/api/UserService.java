package com.capgemini.wsb.fitnesstracker.user.api;

import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    User createUser(User user);

    Optional<User> getUser(Long id);

    List<User> findAllUsers();

    boolean deleteUser(Long id);

    Optional<User> updateUser(Long id, User newUser);

    Optional<User> getUserByEmail(String email);

    List<User> findUsersOlderThan(LocalDate time);

}
