package com.mateuszroszkowski.ConferenceManager.service;

public interface LectureService {

    void addLecture(String subject, String description, String speakerUsername, int path);
}
