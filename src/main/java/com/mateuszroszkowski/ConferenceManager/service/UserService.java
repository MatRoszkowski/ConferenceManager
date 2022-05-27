package com.mateuszroszkowski.ConferenceManager.service;

import com.mateuszroszkowski.ConferenceManager.dto.UserDto;
import com.mateuszroszkowski.ConferenceManager.model.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);

    void updateEmail(String username, String newEmail);

    List<User> getAllUsers();
}
