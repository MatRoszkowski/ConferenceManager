package com.mateuszroszkowski.ConferenceManager.controller;

import com.mateuszroszkowski.ConferenceManager.dto.UserDto;
import com.mateuszroszkowski.ConferenceManager.model.User;
import com.mateuszroszkowski.ConferenceManager.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping("/")
    public void createUser(@RequestBody UserDto userDto) throws RuntimeException {
        try {
            userService.createUser(userDto);
        } catch (RuntimeException runtimeException) {
            throw runtimeException;
        }
    }

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PatchMapping("/email/update/")
    public void updateEmail(@RequestParam String username, @RequestParam String newEmail) {
        try {
            userService.updateEmail(username, newEmail);
        } catch (RuntimeException runtimeException) {
            throw runtimeException;
        }
    }
}
