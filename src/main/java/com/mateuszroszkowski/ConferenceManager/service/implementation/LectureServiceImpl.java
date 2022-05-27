package com.mateuszroszkowski.ConferenceManager.service.implementation;

import com.mateuszroszkowski.ConferenceManager.dto.LectureStartEndDto;
import com.mateuszroszkowski.ConferenceManager.enums.Path;
import com.mateuszroszkowski.ConferenceManager.model.AppUser;
import com.mateuszroszkowski.ConferenceManager.model.Lecture;
import com.mateuszroszkowski.ConferenceManager.repository.LectureRepository;
import com.mateuszroszkowski.ConferenceManager.repository.UserRepository;
import com.mateuszroszkowski.ConferenceManager.service.LectureService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class LectureServiceImpl implements LectureService {
    private final LectureRepository lectureRepository;
    private final UserRepository userRepository;

    @Override
    public void addLecture(String subject, String description, String speakerName, Path path,
                           LocalDateTime lectureStart, LocalDateTime lectureEnd) {
        Lecture lecture = Lecture.builder()
                .subject(subject)
                .description(description)
                .speakerName(speakerName)
                .path(path)
                .lectureStart(lectureStart)
                .lectureEnd(lectureEnd)
                .build();
        lectureRepository.save(lecture);
    }
}
