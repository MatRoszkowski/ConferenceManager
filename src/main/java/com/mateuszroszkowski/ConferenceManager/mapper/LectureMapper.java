package com.mateuszroszkowski.ConferenceManager.mapper;

import com.mateuszroszkowski.ConferenceManager.dto.LectureDto;
import com.mateuszroszkowski.ConferenceManager.model.Lecture;
import com.mateuszroszkowski.ConferenceManager.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LectureMapper {
    private int MAXIMUM_NUMBER_OF_USERS = 5;

    public LectureDto map(Lecture lecture) {
        return LectureDto.builder()
                .id(lecture.getId())
                .subject(lecture.getSubject())
                .description(lecture.getDescription())
                .lectureStart(lecture.getLectureStart())
                .lectureEnd(lecture.getLectureEnd())
                .speakerName(lecture.getSpeakerName())
                .path(lecture.getPath())
                .percentageOccupancy(calculatePercentageOccupancy(lecture.getListeners(), MAXIMUM_NUMBER_OF_USERS))
                .build();
    }

    private double calculatePercentageOccupancy(List<User> users, int max) {
        double percentageOccupancy = (users.size() / max) * 100;
        return percentageOccupancy;
    }
}
