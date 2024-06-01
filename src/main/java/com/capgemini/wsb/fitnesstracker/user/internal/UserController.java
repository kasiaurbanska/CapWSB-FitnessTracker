package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing users. Provides endpoints for CRUD operations on user data.
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    /**
     * Retrieves a list of all users.
     *
     * @return ResponseEntity containing a list of UserDto and the HTTP status code.
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Retrieves a list of all users with simplified information.
     * This method is an alternative to getAllUsers providing the same information but with a different endpoint.
     *
     * @return ResponseEntity containing a list of UserDto and the HTTP status code.
     */
    @GetMapping(value = "/simple", produces = "application/json")
    public ResponseEntity<List<UserDto>> getAllUsersSimple() {
        List<UserDto> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Retrieves a single user by their ID.
     *
     * @param id the ID of the user to retrieve.
     * @return ResponseEntity containing the UserDto if found, or NotFound status if not found.
     */
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        Optional<UserDto> userDto = userService.getUser(id);
        return userDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Creates a new user based on the provided data.
     *
     * @param userDto Data transfer object containing all necessary details to create a new user.
     * @return ResponseEntity containing the created UserDto and the HTTP status code.
     */
    @PostMapping(produces = "application/json")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.createUser(userDto));
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete.
     * @return ResponseEntity indicating success (204 No Content) or not found (404 Not Found).
     */
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Retrieves users by their email address.
     *
     * @param email the email address to search for users.
     * @return ResponseEntity containing a list of UserDto that match the provided email and the HTTP status code.
     */
    @GetMapping(value = "/email", produces = "application/json")
    public ResponseEntity<List<UserDto>> getUsersByEmail(@RequestParam String email) {
        List<UserDto> users = userService.getUserByEmail(email)
                .stream()
                .toList();
        return ResponseEntity.ok(users);
    }

    /**
     * Retrieves all users who are older than a specified date.
     *
     * @param time the date in "yyyy-MM-dd" format to compare against the users' birthdates.
     * @return ResponseEntity containing a list of UserDto for users older than the specified date and the HTTP status code.
     */
    @GetMapping(value = "/older/{time}", produces = "application/json")
    public ResponseEntity<List<UserDto>> getUsersOlderThan(@PathVariable String time) {
        LocalDate localDate = LocalDate.parse(time);
        List<UserDto> users = userService.findUsersOlderThan(localDate);
        return ResponseEntity.ok(users);
    }

    /**
     * Updates an existing user's information based on the provided user DTO.
     *
     * @param id the ID of the user to update.
     * @param user the user DTO containing the updated data.
     * @return ResponseEntity containing the updated UserDto if found, or NotFound status if not found.
     */
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto user) {
        Optional<UserDto> updatedUserDto = userService.updateUser(id, user);
        return updatedUserDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}