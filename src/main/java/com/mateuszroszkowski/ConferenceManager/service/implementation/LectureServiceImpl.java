package com.mateuszroszkowski.ConferenceManager.service.implementation;

import com.mateuszroszkowski.ConferenceManager.enums.Path;
import com.mateuszroszkowski.ConferenceManager.model.Lecture;
import com.mateuszroszkowski.ConferenceManager.model.User;
import com.mateuszroszkowski.ConferenceManager.repository.LectureRepository;
import com.mateuszroszkowski.ConferenceManager.repository.UserRepository;
import com.mateuszroszkowski.ConferenceManager.service.LectureService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
                .filter(lecture -> lecture.getListeners().contains(user.get())).collect(Collectors.toList());
        return userLectures;
    }

    @Override
    public void registerUserToLecture(String username, String email, int lectureId) throws RuntimeException {
        Optional<User> user = userRepository.findByUsername(username);
        Optional<Lecture> lecture = lectureRepository.findById(Long.valueOf(lectureId));
        List<Lecture> lectures = getUserLectures(username);
        int flag = 0;
        validateData(user, username, lecture, lectureId, lectures, email, flag);
        lecture.get().getListeners().add(user.get());

        lectureRepository.save(lecture.get());
        sendEmailToFile(user.get(), lecture.get());
        log.info("User: " + username + " registered successfully to lecture: " + lectureId);
    }

    public void cancelReservation(String username, String email, int lectureId) throws RuntimeException {
        Optional<User> user = userRepository.findByUsername(username);
        Optional<Lecture> lecture = lectureRepository.findById(Long.valueOf(lectureId));
        List<Lecture> lectures = getUserLectures(username);
        int flag = 1;
        validateData(user, username, lecture, lectureId, lectures, email, flag);

        lecture.get().getListeners().remove(user.get());
        log.info("User: " + username + " canceled reservation successfully to lecture: " + lectureId);
    }

    private boolean validateData(Optional<User> user, String username, Optional<Lecture> lecture, int lectureId,
                                 List<Lecture> lectures, String email, int flag) throws RuntimeException {
        if (user.isEmpty()) {
            throw new RuntimeException("User with username: " + username + " does not exist");
        }
        if (lecture.isEmpty()) {
            throw new RuntimeException("Lecture with id: " + lectureId + " does not exist");
        }
        if (!usernameMatchEmail(user, email)) {
            throw new RuntimeException("Username does not match email");
        }
        if (flag == 0) {
            if (isAlreadyRegisteredToLectureWithId(lectures, lectureId)) {
                throw new RuntimeException("User: " + username + " already registered to lecture: " + lectureId);
            }
            if (isAlreadyRegisteredToLectureAtThisTime(lectures, lecture)) {
                throw new RuntimeException("User: " + username + " already registered to lecture at this time");
            }
            if (isLectureFull(lectureId)) {
                throw new RuntimeException("Lecture: " + lectureId + " is full");
            }
        } else if (flag == 1) {
            if (!isAlreadyRegisteredToLectureWithId(lectures, lectureId)) {
                throw new RuntimeException("User: " + username + " is not registered to lecture: " + lectureId);
            }
        }
        return true;
    }

    @Async
    public void sendEmailToFile(User user, Lecture lecture) {
        try {
            FileWriter myWriter = new FileWriter("powiadomienia.txt", true);
            LocalDateTime timestamp = LocalDateTime.now();
            String userEmail = user.getEmail();
            String contents = "timestamp: " + timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\r\n" +
                    "To: " + userEmail + "\r\n" +
                    "From: IT Conference Manager by Mateusz Roszkowski\r\n" +
                    "Hello " + user.getName() + "(" + user.getUsername() + ") \r\n" +
                    "You have been successfully registered to lecture: " + lecture.getId() + ", " + lecture.getSubject() + ", " + lecture.getPath() + "\r\n" +
                    "Lecture starts at: " + lecture.getLectureStart() + "\r\n" +
                    "and ends at: " + lecture.getLectureEnd() + "\r\n\r\n";
            myWriter.write(contents);
            myWriter.close();
            log.info("Successfully wrote to the file.");
        } catch (IOException e) {
            log.info("An error occurred.");
            e.printStackTrace();
        }
    }

    private boolean isLectureFull(int lectureId) {
        Optional<Lecture> lecture = lectureRepository.findById(Long.valueOf(lectureId));
        int numberOfListeners = lecture.get().getListeners().size();
        if (numberOfListeners >= 5) {
            return true;
        }
        return false;
    }

    private boolean usernameMatchEmail(Optional<User> user, String email) {
        if (user.get().getEmail().equals(email)) {
            return true;
        }
        return false;
    }

    private boolean isAlreadyRegisteredToLectureAtThisTime(List<Lecture> lectures, Optional<Lecture> lecture) {
        boolean conflict = lectures.stream().anyMatch(l -> l.getLectureStart().equals(lecture.get().getLectureStart()));
        return conflict;
    }


    private boolean isAlreadyRegisteredToLectureWithId(List<Lecture> lectures, int lectureId) {
        boolean contains = lectures.stream().anyMatch(lecture -> lecture.getId() == lectureId);
        if (contains) {
            return true;
        }
        return false;
    }
}
