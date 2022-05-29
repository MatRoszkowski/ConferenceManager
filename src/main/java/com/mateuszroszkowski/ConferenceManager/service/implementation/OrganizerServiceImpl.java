package com.mateuszroszkowski.ConferenceManager.service.implementation;

import com.mateuszroszkowski.ConferenceManager.dto.LectureDto;
import com.mateuszroszkowski.ConferenceManager.dto.PathDto;
import com.mateuszroszkowski.ConferenceManager.enums.Path;
import com.mateuszroszkowski.ConferenceManager.mapper.LectureMapper;
import com.mateuszroszkowski.ConferenceManager.model.Lecture;
import com.mateuszroszkowski.ConferenceManager.repository.LectureRepository;
import com.mateuszroszkowski.ConferenceManager.service.OrganizerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class OrganizerServiceImpl implements OrganizerService {
    private final LectureRepository lectureRepository;
    private final LectureMapper lectureMapper;
    private int MAXIMUM_NUMBER_OF_USERS_IN_ONE_PATH = 15;

    @Override
    public List<LectureDto> getListOfLecturesAccordingToInterest() {
        List<Lecture> lectures = lectureRepository.findAll();
        List<LectureDto> lectureDtos = lectures.stream()
                .map(lecture -> lectureMapper.map(lecture))
                .sorted(Comparator.comparingDouble(LectureDto::getPercentageOccupancy).reversed())
                .collect(Collectors.toList());
        return lectureDtos;
    }

    @Override
    public List<PathDto> getListOfPathsAccordingToInterest() {
        List<Lecture> lectures = lectureRepository.findAll();
        PathDto pathDtoA = buildPathDto(lectures, Path.PATH_A);
        PathDto pathDtoB = buildPathDto(lectures, Path.PATH_B);
        PathDto pathDtoC = buildPathDto(lectures, Path.PATH_C);

        List<PathDto> paths = new ArrayList<>();
        paths.add(pathDtoA);
        paths.add(pathDtoB);
        paths.add(pathDtoC);
        return paths.stream().sorted(Comparator.comparingDouble(PathDto::getPercentageOccupancy).reversed())
                .collect(Collectors.toList());
    }

    private PathDto buildPathDto(List<Lecture> lectures, Path path) {
        int numberOfListenersInPath = getNumberOfListenersInPath(lectures, path);
        return PathDto.builder()
                .path(path)
                .numberOfListeners(numberOfListenersInPath)
                .percentageOccupancy(calculatePercentageOccupancy(numberOfListenersInPath, MAXIMUM_NUMBER_OF_USERS_IN_ONE_PATH))
                .build();
    }

    private double calculatePercentageOccupancy(int numberOfListenersInPath, int max) {
        double percentageOccupancy = ((double) numberOfListenersInPath / (double) max) * 100.00;
        percentageOccupancy = Math.round(percentageOccupancy * 100.0) / 100.0;
        return percentageOccupancy;
    }

    private int getNumberOfListenersInPath(List<Lecture> lectures, Path path) {
        List<Lecture> listOfLecturesWithPath = lectures.stream()
                .filter(lecture -> lecture.getPath().equals(path)).collect(Collectors.toList());
        int counter = 0;
        for (int i = 0; i < listOfLecturesWithPath.size(); i++) {
            counter += listOfLecturesWithPath.get(i).getListeners().size();
        }
        return counter;
    }
}
