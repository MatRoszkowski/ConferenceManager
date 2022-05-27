package com.mateuszroszkowski.ConferenceManager.repository;

import com.mateuszroszkowski.ConferenceManager.enums.Path;
import com.mateuszroszkowski.ConferenceManager.model.AppUser;
import com.mateuszroszkowski.ConferenceManager.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {

    @Query("SELECT l FROM Lecture l WHERE l.path = ?1 OR l.path = ?2 OR l.path = ?3")
    List<Lecture> findAllWithHours(Path path1, Path path2, Path path3);

}
