package com.mateuszroszkowski.ConferenceManager.controller;

import com.mateuszroszkowski.ConferenceManager.model.Lecture;
import com.mateuszroszkowski.ConferenceManager.service.LectureService;
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

    @GetMapping("/")
    public List<Lecture> getAllLectures() {
        return lectureService.getAllLectures();
    }

    @GetMapping("/{username}")
    public List<Lecture> getUserLectures(String username){
        return lectureService.getUserLectures(username);
    }

    @PutMapping("/")
    public void registerUserToLecture(@RequestParam String username, @RequestParam String email, @RequestParam int lectureId){
        lectureService.registerUserToLecture(username, email, lectureId);
    }
}
