package com.mateuszroszkowski.ConferenceManager.mapper;

import com.mateuszroszkowski.ConferenceManager.dto.UserDto;
import com.mateuszroszkowski.ConferenceManager.model.AppUser;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public AppUser map(UserDto userDto){
        return AppUser.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .build();
    }

    public UserDto map(AppUser appUser){
        return UserDto.builder().build();
    }
}
