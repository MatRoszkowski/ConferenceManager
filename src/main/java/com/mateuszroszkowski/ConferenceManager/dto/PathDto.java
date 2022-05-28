package com.mateuszroszkowski.ConferenceManager.dto;

import com.mateuszroszkowski.ConferenceManager.enums.Path;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PathDto {

    private Path path;
    private int numberOfListeners;
    private double percentageOccupancy;

}
