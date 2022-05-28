package com.mateuszroszkowski.ConferenceManager.model;

import com.mateuszroszkowski.ConferenceManager.enums.Path;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "lectures")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subject;
    private String description;
    private LocalDateTime lectureStart;
    private LocalDateTime lectureEnd;
    private String speakerName;
    @ManyToMany
    @Size(min = 1, max = 5)
    private List<User> listeners = new ArrayList<>();
    private Path path;

}
