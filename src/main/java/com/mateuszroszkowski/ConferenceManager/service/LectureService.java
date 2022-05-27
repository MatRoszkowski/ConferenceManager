package com.mateuszroszkowski.ConferenceManager.service;

import com.mateuszroszkowski.ConferenceManager.enums.Path;
import com.mateuszroszkowski.ConferenceManager.model.Lecture;
import com.mateuszroszkowski.ConferenceManager.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface LectureService {

    void addLecture(String subject, String description, String speakerUsername, Path path,
                    LocalDateTime lectureStart, LocalDateTime lectureEnd);

    List<Lecture> getAllLectures();

    List<Lecture> getUserLectures(String username);

    void registerUserToLecture(String username, String email, int lectureId);
}
