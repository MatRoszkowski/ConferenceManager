package com.mateuszroszkowski.ConferenceManager.service.implementation;

import com.mateuszroszkowski.ConferenceManager.enums.Path;
import com.mateuszroszkowski.ConferenceManager.model.Lecture;
import com.mateuszroszkowski.ConferenceManager.model.User;
import com.mateuszroszkowski.ConferenceManager.repository.LectureRepository;
import com.mateuszroszkowski.ConferenceManager.repository.UserRepository;
import com.mateuszroszkowski.ConferenceManager.service.LectureService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
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
        log.info("Lecture successfully created");
    }

    @Override
    public List<Lecture> getAllLectures() {
        return lectureRepository.findAll();
    }

    @Override
    public List<Lecture> getUserLectures(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new RuntimeException("User with username: " + username + " does not exist");
        }
        List<Lecture> lectures = lectureRepository.findAll();
        List<Lecture> userLectures = lectures.stream()
                .filter(lecture -> lecture.getListeners().contains(user)).collect(Collectors.toList());
        return userLectures;
    }

    @Override
    public void registerUserToLecture(String username, String email, int lectureId) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new RuntimeException("User with username: " + username + " does not exist");
        }
        Optional<Lecture> lecture = lectureRepository.findById(Long.valueOf(lectureId));
        if (lecture.isEmpty()) {
            throw new RuntimeException("Lecture with id: " + lectureId + " does not exist");
        }
        if (!usernameMatchEmail(username,email)){
            throw new RuntimeException("Username does not match email");
        }
        if (isAlreadyRegisteredToLectureWithId(username,lectureId)){
            throw new RuntimeException("User: " + username + " already registered to lecture: " + lectureId);
        }


    }

    private boolean usernameMatchEmail(String username, String email){
        Optional<User> user = userRepository.findByUsername(username);
        if (user.get().getEmail().equals(email)){
            return true;
        }
        return false;
    }
    private boolean isAlreadyRegisteredToLectureAtThisTime(String username, int lectureId){
        List<Lecture> lectures = getUserLectures(username);
        Optional<Lecture> lecture = lectureRepository.findById(Long.valueOf(lectureId));
    }


    private boolean isAlreadyRegisteredToLectureWithId(String username, int lectureId) {
        List<Lecture> lectures = getUserLectures(username);
        boolean contains = lectures.stream().anyMatch(lecture -> lecture.getId() == lectureId);
        if (contains) {
            return true;
        }
        return false;
    }
}
