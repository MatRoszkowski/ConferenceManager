package com.mateuszroszkowski.ConferenceManager.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LectureStartEndDto {
    private LocalDateTime lectureStart;
    private LocalDateTime lectureEnd;
}
