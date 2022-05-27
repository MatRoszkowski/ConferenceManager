package com.mateuszroszkowski.ConferenceManager.service.implementation;

import com.mateuszroszkowski.ConferenceManager.repository.LectureRepository;
import com.mateuszroszkowski.ConferenceManager.service.LectureService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class LectureServiceImpl implements LectureService {
    private final LectureRepository lectureRepository;
}
