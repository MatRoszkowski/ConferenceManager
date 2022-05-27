package com.mateuszroszkowski.ConferenceManager.repository;

import com.mateuszroszkowski.ConferenceManager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
