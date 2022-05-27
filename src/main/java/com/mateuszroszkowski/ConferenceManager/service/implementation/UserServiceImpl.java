package com.mateuszroszkowski.ConferenceManager.service.implementation;

import com.mateuszroszkowski.ConferenceManager.dto.UserDto;
import com.mateuszroszkowski.ConferenceManager.mapper.UserMapper;
import com.mateuszroszkowski.ConferenceManager.model.User;
import com.mateuszroszkowski.ConferenceManager.repository.UserRepository;
import com.mateuszroszkowski.ConferenceManager.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto createUser(UserDto userDto) throws RuntimeException {
        if (userWithUsernameExists(userDto.getUsername())) {
            throw new RuntimeException("User with username: " + userDto.getUsername() + " already exists");
        }
        if (userWithEmailExists(userDto.getEmail())) {
            throw new RuntimeException("User with email: " + userDto.getEmail() + " already exists");
        }
        User user = userMapper.map(userDto);
        user = userRepository.save(user);
        log.info("User successfully created");
        return userMapper.map(user);
    }

    private boolean userWithUsernameExists(String username) {
        Optional<User> userByUsername = userRepository.findByUsername(username);
        if (userByUsername.isPresent()) {
            return true;
        }
        return false;
    }

    private boolean userWithEmailExists(String email) {
        Optional<User> userByEmail = userRepository.findByEmail(email);
        if (userByEmail.isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public void updateEmail(String username, String newEmail) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            user.get().setEmail(newEmail);
            userRepository.save(user.get());
            log.info("Email successfully updated");
        } else {
            throw new RuntimeException("User with username: " + username + " does not exist");
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
