package com.example.Website.domain.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ProjectResponseDto {
    private Long id;
    private String title;
    private String summary;
    private String description;
    private String link;
    private List<String> techStack;
}
