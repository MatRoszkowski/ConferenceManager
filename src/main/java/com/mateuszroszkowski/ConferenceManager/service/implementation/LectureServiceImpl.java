package com.mateuszroszkowski.ConferenceManager.service.implementation;

import com.mateuszroszkowski.ConferenceManager.dto.LectureStartEndDto;
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
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class LectureServiceImpl implements LectureService {
    private final LectureRepository lectureRepository;
    private final UserRepository userRepository;

    @Override
    public void addLecture(String subject, String description, String speakerUsername, int path) throws RuntimeException {
        if (path < 0 || path > 8) {
            throw new RuntimeException("Path with number: " + path + " does not exist");
        }
        Optional<AppUser> speaker = userRepository.findByUsername(speakerUsername);
        if (speaker.isEmpty()) {
            throw new RuntimeException("Speaker with username: " + speakerUsername + " does not exist");
        }
        if (!userCanParticipate(speaker.get(), path)) {
            throw new RuntimeException("Speaker with this username already has a lecture at this time");
        }
        LectureStartEndDto lectureStartEndDto = getLectureStarEnd(path);
        Lecture lecture = Lecture.builder()
                .subject(subject)
                .description(description)
                .speaker(speaker.get())
                .lectureStart(lectureStartEndDto.getLectureStart())
                .lectureEnd(lectureStartEndDto.getLectureEnd())
                .path(path)
                .build();
        lectureRepository.save(lecture);
    }

    private boolean userCanParticipate(AppUser user, int path) {
        List<Lecture> lecturesAtGivenTime = new ArrayList<>();
        if (path == 0 || path == 1 || path == 2) {
            lecturesAtGivenTime = lectureRepository.findAllWithHours(0, 1, 2);
        } else if (path == 3 || path == 4 || path == 5) {
            lecturesAtGivenTime = lectureRepository.findAllWithHours(3, 4, 5);
        } else if (path == 6 || path == 7 || path == 8) {
            lecturesAtGivenTime = lectureRepository.findAllWithHours(6, 7, 8);
        }
        List<Lecture> isSpeaker = lecturesAtGivenTime.stream()
                .filter(lecture -> lecture.getSpeaker().getUsername().equals(user.getUsername())).collect(Collectors.toList());
        if (!isSpeaker.isEmpty()) {
            return false;
        }
        List<Lecture> isListener = lecturesAtGivenTime.stream()
                .filter(lecture -> lecture.getListeners().contains(user)).collect(Collectors.toList());
        if (!isListener.isEmpty()) {
            return false;
        }
        return true;
    }

    private LectureStartEndDto getLectureStarEnd(int path) {
        LectureStartEndDto lectureStartEndDto = null;
        if (path == 0 || path == 1 || path == 2) {
            lectureStartEndDto = LectureStartEndDto.builder()
                    .lectureStart(LocalDateTime.of(2022, Month.JUNE, 1, 10, 0))
                    .lectureEnd(LocalDateTime.of(2022, Month.JUNE, 1, 11, 45))
                    .build();
        } else if (path == 3 || path == 4 || path == 5) {
            lectureStartEndDto = LectureStartEndDto.builder()
                    .lectureStart(LocalDateTime.of(2022, Month.JUNE, 1, 12, 0))
                    .lectureEnd(LocalDateTime.of(2022, Month.JUNE, 1, 13, 45))
                    .build();
        } else if (path == 6 || path == 7 || path == 8) {
            lectureStartEndDto = LectureStartEndDto.builder()
                    .lectureStart(LocalDateTime.of(2022, Month.JUNE, 1, 14, 0))
                    .lectureEnd(LocalDateTime.of(2022, Month.JUNE, 1, 15, 45))
                    .build();
        }
        return lectureStartEndDto;
    }
}
