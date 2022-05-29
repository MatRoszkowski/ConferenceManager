package com.mateuszroszkowski.ConferenceManager.controller;

import com.mateuszroszkowski.ConferenceManager.dto.UserDto;
import com.mateuszroszkowski.ConferenceManager.model.User;
import com.mateuszroszkowski.ConferenceManager.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Create user", description = "Using this endpoint you can create new user in the system. User username and email" +
            " must be unique. If user with this username or emails exists in the system response contains a message about it.")
    @PostMapping("/")
    public void createUser(@RequestBody UserDto userDto) throws RuntimeException {
        try {
            userService.createUser(userDto);
        } catch (RuntimeException runtimeException) {
            throw runtimeException;
        }
    }

    @Operation(summary = "Get all users", description = "Returns all users in the system")
    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "Update email", description = "Using this endpoint you can update user's email address. System checks if" +
            " user with given username exists. If not the message about it is returned")
    @PatchMapping("/email/")
    public void updateEmail(@RequestParam String username, @RequestParam String newEmail) {
        try {
            userService.updateEmail(username, newEmail);
        } catch (RuntimeException runtimeException) {
            throw runtimeException;
        }
    }
}
