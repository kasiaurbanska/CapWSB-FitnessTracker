package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto createUser(UserDto newUserDto) {
        log.info("Creating User {}", newUserDto);

        if (newUserDto.id() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        User createdUser = userRepository.save(userMapper.toEntity(newUserDto));

        return userMapper.toDto(createdUser);
    }

    @Override
    public Optional<UserDto> getUser(final Long userId) {
        log.info("getUser()");
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent())
        {
            log.info("user present");
        }
        else
        {
            log.info("user NULL");
        }
        return user.map(userMapper::toDto).or(Optional::empty);
    }

    @Override
    public Optional<UserDto> getUserByEmail(final String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.map(userMapper::toDto).or(Optional::empty);
    }

    @Override
    public List<UserDto> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<UserDto> updateUser(Long id, UserDto newUserDto) {
        return userRepository.findById(id).map(user -> {
            user.setFirstName(newUserDto.firstName());
            user.setLastName(newUserDto.lastName());
            user.setBirthdate(newUserDto.birthdate());
            user.setEmail(newUserDto.email());
            return userMapper.toDto(userRepository.save(user));
        });
    }

    @Override
    public List<UserDto> findUsersOlderThan(LocalDate time) {
        return userRepository.findAll()
                .stream()
                .filter(user -> time.isAfter(user.getBirthdate()))
                .map(userMapper::toDto)
                .toList();
    }
}