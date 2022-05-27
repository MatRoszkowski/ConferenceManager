package com.mateuszroszkowski.ConferenceManager.service.implementation;

import com.mateuszroszkowski.ConferenceManager.dto.UserDto;
import com.mateuszroszkowski.ConferenceManager.mapper.UserMapper;
import com.mateuszroszkowski.ConferenceManager.model.AppUser;
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
    public void createUser(UserDto userDto) throws RuntimeException {
        checkIfUserExists(userDto);
        AppUser appUser = userMapper.map(userDto);
        userRepository.save(appUser);
        log.info("User successfully created");
    }

    private void checkIfUserExists(UserDto userDto) throws RuntimeException {
        Optional<AppUser> userByUsername = userRepository.findByUsername(userDto.getUsername());
        Optional<AppUser> userByEmail = userRepository.findByEmail(userDto.getEmail());
        if (userByUsername.isPresent()) {
            throw new RuntimeException("User with username: " + userDto.getUsername() + " already exists");
        }
        if (userByEmail.isPresent()) {
            throw new RuntimeException("User with email: " + userDto.getEmail() + " already exists");
        }
    }

    @Override
    public void updateEmail(String username, String newEmail) {
        Optional<AppUser> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            user.get().setEmail(newEmail);
            userRepository.save(user.get());
            log.info("Email successfully updated");
        } else {
            throw new RuntimeException("User with username: " + username + " does not exist");
        }
    }

    @Override
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }
}
