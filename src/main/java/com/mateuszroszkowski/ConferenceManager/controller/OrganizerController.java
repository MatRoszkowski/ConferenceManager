package com.mateuszroszkowski.ConferenceManager.controller;

import com.mateuszroszkowski.ConferenceManager.dto.LectureDto;
import com.mateuszroszkowski.ConferenceManager.model.Lecture;
import com.mateuszroszkowski.ConferenceManager.service.OrganizerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/organizer/lectures")
@AllArgsConstructor
@Slf4j
public class OrganizerController {

    private final OrganizerService organizerService;

    @GetMapping("/")
    public List<LectureDto> getListOfLecturesAccordingToInterest() {
        return organizerService.getListOfLecturesAccordingToInterest();
    }
}
