package com.mateuszroszkowski.ConferenceManager.service.implementation;

import com.mateuszroszkowski.ConferenceManager.dto.LectureDto;
import com.mateuszroszkowski.ConferenceManager.dto.PathDto;
import com.mateuszroszkowski.ConferenceManager.mapper.LectureMapper;
import com.mateuszroszkowski.ConferenceManager.model.Lecture;
import com.mateuszroszkowski.ConferenceManager.repository.LectureRepository;
import com.mateuszroszkowski.ConferenceManager.repository.UserRepository;
import com.mateuszroszkowski.ConferenceManager.service.OrganizerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class OrganizerServiceImpl implements OrganizerService {
    private final LectureRepository lectureRepository;
    private final UserRepository userRepository;
    private final LectureMapper lectureMapper;

    @Override
    public List<LectureDto> getListOfLecturesAccordingToInterest() {
        List<Lecture> lectures = lectureRepository.findAll();
        List<LectureDto> lectureDtos = lectures.stream()
                .sorted(Comparator.comparing(lecture -> lecture.getListeners().size()))
                .map(lecture -> lectureMapper.map(lecture)).collect(Collectors.toList());
        return lectureDtos;
    }

    @Override
    public List<PathDto> getListOfPathsAccordingToInterest() {
        return null;
    }
}
