package com.mateuszroszkowski.ConferenceManager.service.implementation;

import com.mateuszroszkowski.ConferenceManager.repository.UserRepository;
import com.mateuszroszkowski.ConferenceManager.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
}
