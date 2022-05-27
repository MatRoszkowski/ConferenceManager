package com.mateuszroszkowski.ConferenceManager.model;

import com.mateuszroszkowski.ConferenceManager.enums.Path;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "lectures")
@Builder
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subject;
    private String description;
    private LocalDateTime start;
    private LocalDateTime end;
    @OneToOne
    private User speaker;
    @OneToMany
    @Size(min=1, max=10)
    private Set<User> listeners = new HashSet<>();
    private Path path;

}
