package com.mateuszroszkowski.ConferenceManager.service;

import com.mateuszroszkowski.ConferenceManager.dto.LectureDto;
import com.mateuszroszkowski.ConferenceManager.dto.PathDto;

import java.util.List;

public interface OrganizerService {
    List<LectureDto> getListOfLecturesAccordingToInterest();

    List<PathDto> getListOfPathsAccordingToInterest();
}
