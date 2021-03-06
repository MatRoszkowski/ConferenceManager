package com.mateuszroszkowski.ConferenceManager;

import com.mateuszroszkowski.ConferenceManager.dto.UserDto;
import com.mateuszroszkowski.ConferenceManager.enums.Path;
import com.mateuszroszkowski.ConferenceManager.service.LectureService;
import com.mateuszroszkowski.ConferenceManager.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;

@SpringBootApplication
@EnableAsync
@Slf4j
public class ConferenceManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConferenceManagerApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(UserService userService, LectureService lectureService) {
        return args -> {
            try {
                File mailFile = new File("powiadomienia.txt");
                if (mailFile.createNewFile()) {
                    log.info("File created: " + mailFile.getName());
                } else {
                    log.info("File already exists.");
                }
            } catch (IOException e) {
                log.info("An error occurred.");
                e.printStackTrace();
            }
            UserDto userDto1 = UserDto.builder()
                    .name("Jan")
                    .username("test1")
                    .email("email1")
                    .build();
            UserDto userDto2 = UserDto.builder()
                    .name("Kasia")
                    .username("test2")
                    .email("email2")
                    .build();
            UserDto userDto3 = UserDto.builder()
                    .name("Mateusz")
                    .username("test3")
                    .email("email3")
                    .build();
            UserDto userDto4 = UserDto.builder()
                    .name("Anna")
                    .username("test4")
                    .email("email4")
                    .build();
            UserDto userDto5 = UserDto.builder()
                    .name("Wincent")
                    .username("test5")
                    .email("email5")
                    .build();
            UserDto userDto6 = UserDto.builder()
                    .name("Klementyna")
                    .username("test6")
                    .email("email6")
                    .build();
            UserDto userDto7 = UserDto.builder()
                    .name("Bogumi??")
                    .username("test7")
                    .email("email7")
                    .build();
            UserDto userDto8 = UserDto.builder()
                    .name("Bogumi??a")
                    .username("test8")
                    .email("email8")
                    .build();

            userService.createUser(userDto1);
            userService.createUser(userDto2);
            userService.createUser(userDto3);
            userService.createUser(userDto4);
            userService.createUser(userDto5);
            userService.createUser(userDto6);
            userService.createUser(userDto7);
            userService.createUser(userDto8);

            lectureService.addLecture("Subject 1", "First lecture on path A", "Jan Kowalski", Path.PATH_A,
                    LocalDateTime.of(2022, Month.JUNE, 1, 10, 0),
                    LocalDateTime.of(2022, Month.JUNE, 1, 11, 45));
            lectureService.addLecture("Subject 2", "First lecture on path B", "Adam Nowak", Path.PATH_B,
                    LocalDateTime.of(2022, Month.JUNE, 1, 10, 0),
                    LocalDateTime.of(2022, Month.JUNE, 1, 11, 45));
            lectureService.addLecture("Subject 3", "First lecture on path C", "Anna Wi??niewska", Path.PATH_C,
                    LocalDateTime.of(2022, Month.JUNE, 1, 10, 0),
                    LocalDateTime.of(2022, Month.JUNE, 1, 11, 45));

            lectureService.addLecture("Subject 1", "Second lecture on path A", "Jan Kowalski", Path.PATH_A,
                    LocalDateTime.of(2022, Month.JUNE, 1, 12, 0),
                    LocalDateTime.of(2022, Month.JUNE, 1, 13, 45));
            lectureService.addLecture("Subject 2", "Second lecture on path B", "Adam Nowak", Path.PATH_B,
                    LocalDateTime.of(2022, Month.JUNE, 1, 12, 0),
                    LocalDateTime.of(2022, Month.JUNE, 1, 13, 45));
            lectureService.addLecture("Subject 3", "Second lecture on path C", "Anna Wi??niewska", Path.PATH_C,
                    LocalDateTime.of(2022, Month.JUNE, 1, 12, 0),
                    LocalDateTime.of(2022, Month.JUNE, 1, 13, 45));

            lectureService.addLecture("Subject 1", "Third lecture on path A", "Jan Kowalski", Path.PATH_A,
                    LocalDateTime.of(2022, Month.JUNE, 1, 14, 0),
                    LocalDateTime.of(2022, Month.JUNE, 1, 15, 45));
            lectureService.addLecture("Subject 2", "Third lecture on path B", "Adam Nowak", Path.PATH_B,
                    LocalDateTime.of(2022, Month.JUNE, 1, 14, 0),
                    LocalDateTime.of(2022, Month.JUNE, 1, 15, 45));
            lectureService.addLecture("Subject 3", "Third lecture on path C", "Anna Wi??niewska", Path.PATH_C,
                    LocalDateTime.of(2022, Month.JUNE, 1, 14, 0),
                    LocalDateTime.of(2022, Month.JUNE, 1, 15, 45));
        };
    }
}
