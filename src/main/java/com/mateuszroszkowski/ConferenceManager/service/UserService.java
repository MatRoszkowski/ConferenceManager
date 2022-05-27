package com.mateuszroszkowski.ConferenceManager.service;

import com.mateuszroszkowski.ConferenceManager.dto.UserDto;
import com.mateuszroszkowski.ConferenceManager.model.AppUser;

import java.util.List;

public interface UserService {
    void createUser(UserDto userDto);

    void updateEmail(String username, String newEmail);

    List<AppUser> getAllUsers();
}
