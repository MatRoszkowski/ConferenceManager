package com.mateuszroszkowski.ConferenceManager.controller;

import com.mateuszroszkowski.ConferenceManager.dto.UserDto;
import com.mateuszroszkowski.ConferenceManager.service.LectureService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lectures")
@AllArgsConstructor
@Slf4j
public class LectureController {
    private final LectureService lectureService;

    @PostMapping("/")
    public void addLecture(@RequestParam String subject, @RequestParam String description,
                           @RequestParam String speakerUsername, @RequestParam int path) throws RuntimeException {
        try {
            lectureService.addLecture(subject, description, speakerUsername, path);
        } catch (RuntimeException runtimeException) {
            throw runtimeException;
        }
    }
}
