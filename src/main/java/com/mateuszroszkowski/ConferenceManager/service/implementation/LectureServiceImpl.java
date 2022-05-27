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
        Path path1 = getPathById(path);
        if (path1 == null) {
            throw new RuntimeException("Path with number: " + path + " does not exist");
        }
        Optional<AppUser> speaker = userRepository.findByUsername(speakerUsername);
        if (speaker.isEmpty()) {
            throw new RuntimeException("Speaker with username: " + speakerUsername + " does not exist");
        }
        if (!userCanParticipate(speaker.get(), path1)) {
            throw new RuntimeException("Speaker with this username already has a lecture at this time");
        }
        LectureStartEndDto lectureStartEndDto = getLectureStarEnd(path1);
        Lecture lecture = Lecture.builder()
                .subject(subject)
                .description(description)
                .speaker(speaker.get())
                .lectureStart(lectureStartEndDto.getLectureStart())
                .lectureEnd(lectureStartEndDto.getLectureEnd())
                .path(path1)
                .build();
        lectureRepository.save(lecture);
    }

    private boolean userCanParticipate(AppUser user, Path path) {
        List<Lecture> lecturesAtGivenTime = new ArrayList<>();
        if (path == Path.PATH_A_1 || path == Path.PATH_A_2 || path == Path.PATH_A_3) {
            lecturesAtGivenTime = lectureRepository.findAllWithHours(Path.PATH_A_1, Path.PATH_A_2, Path.PATH_A_3);
        } else if (path == Path.PATH_B_1 || path == Path.PATH_B_2 || path == Path.PATH_B_3) {
            lecturesAtGivenTime = lectureRepository.findAllWithHours(Path.PATH_B_1, Path.PATH_B_2, Path.PATH_B_3);
        } else if (path == Path.PATH_C_1 || path == Path.PATH_C_2 || path == Path.PATH_C_3) {
            lecturesAtGivenTime = lectureRepository.findAllWithHours(Path.PATH_C_1, Path.PATH_C_2, Path.PATH_C_3);
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

    private LectureStartEndDto getLectureStarEnd(Path path) {
        LectureStartEndDto lectureStartEndDto = null;
        if (path == Path.PATH_A_1 || path == Path.PATH_A_2 || path == Path.PATH_A_3) {
            lectureStartEndDto = LectureStartEndDto.builder()
                    .lectureStart(LocalDateTime.of(2022, Month.JUNE, 1, 10, 0))
                    .lectureEnd(LocalDateTime.of(2022, Month.JUNE, 1, 11, 45))
                    .build();
        } else if (path == Path.PATH_B_1 || path == Path.PATH_B_2 || path == Path.PATH_B_3) {
            lectureStartEndDto = LectureStartEndDto.builder()
                    .lectureStart(LocalDateTime.of(2022, Month.JUNE, 1, 12, 0))
                    .lectureEnd(LocalDateTime.of(2022, Month.JUNE, 1, 13, 45))
                    .build();
        } else if (path == Path.PATH_C_1 || path == Path.PATH_C_2 || path == Path.PATH_C_3) {
            lectureStartEndDto = LectureStartEndDto.builder()
                    .lectureStart(LocalDateTime.of(2022, Month.JUNE, 1, 14, 0))
                    .lectureEnd(LocalDateTime.of(2022, Month.JUNE, 1, 15, 45))
                    .build();
        }
        return lectureStartEndDto;
    }

    private Path getPathById(int id) {
        if (id < 0 && id > 8) {
            throw new RuntimeException("Path must be in the range 0-8");
        }
        Path path;
        switch (id) {
            case 0:
                path = Path.PATH_A_1;
                break;
            case 1:
                path = Path.PATH_A_2;
                break;
            case 2:
                path = Path.PATH_A_3;
                break;
            case 3:
                path = Path.PATH_B_1;
                break;
            case 4:
                path = Path.PATH_B_2;
                break;
            case 5:
                path = Path.PATH_B_3;
                break;
            case 6:
                path = Path.PATH_C_1;
                break;
            case 7:
                path = Path.PATH_C_2;
                break;
            case 8:
                path = Path.PATH_C_3;
                break;
            default:
                path = null;
        }
        return path;
    }
}