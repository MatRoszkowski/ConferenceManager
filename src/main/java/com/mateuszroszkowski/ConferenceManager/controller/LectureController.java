package com.mateuszroszkowski.ConferenceManager.controller;

import com.mateuszroszkowski.ConferenceManager.model.Lecture;
import com.mateuszroszkowski.ConferenceManager.service.LectureService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lectures")
@AllArgsConstructor
@Slf4j
public class LectureController {
    private final LectureService lectureService;

    @Operation(summary = "Get all lectures", description = "Returns a list of lectures.")
    @GetMapping("/")
    public List<Lecture> getAllLectures() {
        return lectureService.getAllLectures();
    }

    @Operation(summary = "Get all user lectures", description = "Returns a list of lectures user is signed in. If user does not exist" +
            " returns message informing about it.")
    @GetMapping("/{username}/")
    public List<Lecture> getUserLectures(String username) {
        return lectureService.getUserLectures(username);
    }

    @Operation(summary = "Reserve a spot in lecture", description = "Using this endpoint you can register user to chosen lecture." +
            " method takes username, email and lecture id as parameters. Checks if user with username and email exists, if username" +
            " and email match each other, if user is already registered to given lecture and if lecture is already full. " +
            "Returns a message if any of this conditions is not fulfilled. Also sends 'email' to file named 'powiadomienia.txt'.")
    @PatchMapping("/")
    public void registerUserToLecture(@RequestParam String username, @RequestParam String email, @RequestParam int lectureId) throws RuntimeException {
        lectureService.registerUserToLecture(username, email, lectureId);
    }

    @Operation(summary = "Cancer reservation in lecture", description = "Using this endpoint you can cancel reservation to lecture." +
            " method takes username, email and lecture id as parameters. Checks if user with username and email exists, if username" +
            " and email match each other, if user is registered to given lecture. Returns a message if any of this conditions is not fulfilled.")
    @PatchMapping("/cancel/")
    public void cancelReservation(@RequestParam String username, @RequestParam String email, @RequestParam int lectureId) throws RuntimeException {
        lectureService.cancelReservation(username, email, lectureId);
    }
}
