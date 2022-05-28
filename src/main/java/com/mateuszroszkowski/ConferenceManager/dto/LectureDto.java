package com.mateuszroszkowski.ConferenceManager.dto;

import com.mateuszroszkowski.ConferenceManager.enums.Path;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LectureDto {

    private Long id;
    private String subject;
    private String description;
    private LocalDateTime lectureStart;
    private LocalDateTime lectureEnd;
    private String speakerName;
    private Path path;
    private double percentageOccupancy;
}
