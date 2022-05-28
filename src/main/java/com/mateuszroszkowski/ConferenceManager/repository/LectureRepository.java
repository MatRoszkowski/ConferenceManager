package com.mateuszroszkowski.ConferenceManager.repository;

import com.mateuszroszkowski.ConferenceManager.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
