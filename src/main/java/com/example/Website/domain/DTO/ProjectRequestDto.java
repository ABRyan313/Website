package com.example.Website.domain.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProjectRequestDto {
    private String title;
    private String summary;
    private String description;
    private String link;
    private List<String> techStack;
}
