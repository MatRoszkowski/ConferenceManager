package com.mateuszroszkowski.ConferenceManager;

import com.mateuszroszkowski.ConferenceManager.dto.UserDto;
import com.mateuszroszkowski.ConferenceManager.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConferenceManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConferenceManagerApplication.class, args);
    }


    @Bean
    CommandLineRunner runner(UserService userService) {
        return args -> {
            UserDto userDto1 = UserDto.builder()
                    .username("Mateusz1")
                    .email("email1")
                    .build();
            UserDto userDto2 = UserDto.builder()
                    .username("Mateusz2")
                    .email("email2")
                    .build();
            UserDto userDto3 = UserDto.builder()
                    .username("Mateusz3")
                    .email("email3")
                    .build();
            UserDto userDto4 = UserDto.builder()
                    .username("Mateusz4")
                    .email("email4")
                    .build();
            UserDto userDto5 = UserDto.builder()
                    .username("Mateusz5")
                    .email("email5")
                    .build();

            userService.createUser(userDto1);
            userService.createUser(userDto2);
            userService.createUser(userDto3);
            userService.createUser(userDto4);
            userService.createUser(userDto5);

        };
    }

}
