package com.mateuszroszkowski.ConferenceManager.service;

import com.mateuszroszkowski.ConferenceManager.enums.Path;

import java.time.LocalDateTime;

public interface LectureService {

    void addLecture(String subject, String description, String speakerUsername, Path path,
                    LocalDateTime lectureStart, LocalDateTime lectureEnd);
}
