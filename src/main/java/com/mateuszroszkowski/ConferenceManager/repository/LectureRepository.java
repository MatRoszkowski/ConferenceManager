package com.mateuszroszkowski.ConferenceManager.repository;

import com.mateuszroszkowski.ConferenceManager.enums.Path;
import com.mateuszroszkowski.ConferenceManager.model.AppUser;
import com.mateuszroszkowski.ConferenceManager.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {
    @Query("SELECT l FROM lectures l WHERE l.speaker.username = ?1 AND l.path = ?2")
    Optional<Lecture> findByUsernameAndPath(String username, Path path);
}
