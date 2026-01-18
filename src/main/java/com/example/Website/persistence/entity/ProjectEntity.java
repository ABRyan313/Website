package com.example.Website.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "projects")
public class ProjectEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String title;
        @Column(length = 500)
        private String summary;
        @Column(columnDefinition = "TEXT")
        private String description;
        private String link;
        private LocalDate updatedDate;

        @ElementCollection
        private List<String> techStack;
    }