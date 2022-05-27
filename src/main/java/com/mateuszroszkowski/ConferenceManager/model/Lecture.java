package com.mateuszroszkowski.ConferenceManager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "lectures")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String subject;
    private String description;
    private LocalDateTime lectureStart;
    private LocalDateTime lectureEnd;
    @ManyToOne
    private AppUser speaker;
    @OneToMany
    @Size(min=1, max=5)
    private Set<AppUser> listeners = new HashSet<>();
    @Column(unique = true)
    @Min(0)
    @Max(8)
    private Integer path;

}
