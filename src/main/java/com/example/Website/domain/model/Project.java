package com.example.Website.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class Project {

    private Long id;
    private String title;
    private String summary;
    private String description;
    private String link;
    private LocalDate updatedDate;
    private List<String> techStack;
}

