package com.mateuszroszkowski.ConferenceManager.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PathDto {

    private PathDto pathDto;
    private double percentageOccupancy;

}
