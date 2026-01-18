package com.example.Website.service;

import com.example.Website.domain.DTO.ProjectRequestDto;
import com.example.Website.domain.DTO.ProjectResponseDto;

import java.util.List;

public interface ProjectService {
    List<ProjectResponseDto> getAllProjects();
    ProjectResponseDto createProject(ProjectRequestDto dto);
    ProjectResponseDto updateProject(Long id, ProjectRequestDto dto);
    void deleteProject(Long id);
}
