package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    @GetMapping(produces = "application/json")
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/simple", produces = "application/json")
    public List<UserDto> getAllUsersSimple() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        User user = userService.getUser(id).stream().findFirst().orElse(null);
        return userMapper.toDto(user);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) throws InterruptedException {
        return new ResponseEntity<>(userService.createUser(userMapper.toEntity(userDto)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/email", produces = "application/json")
    public List<UserDto> getUsersByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email).stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());

    }

    @GetMapping("/older/{time}")
    public List<UserDto> getUsersOlderThan(@PathVariable String time) {
        LocalDate localDate = LocalDate.parse(time);
        return userService.findUsersOlderThan(localDate).stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody User user) {
        User updated_user = userService.updateUser(id, user).stream().findFirst().orElse(null);
        return userMapper.toDto(updated_user);
    }

}