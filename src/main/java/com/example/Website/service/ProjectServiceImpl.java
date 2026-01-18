package com.example.Website.service;

import com.example.Website.domain.DTO.ProjectRequestDto;
import com.example.Website.domain.DTO.ProjectResponseDto;
import com.example.Website.persistence.entity.ProjectEntity;
import com.example.Website.persistence.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public List<ProjectResponseDto> getAllProjects() {
        return projectRepository.findAllByOrderByUpdatedDateDesc()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ProjectResponseDto createProject(ProjectRequestDto dto) {
        ProjectEntity project = new ProjectEntity();
        applyDto(project, dto);
        project.setUpdatedDate(LocalDate.now());

        return mapToResponse(projectRepository.save(project));
    }

    @Override
    public ProjectResponseDto updateProject(Long id, ProjectRequestDto dto) {
        ProjectEntity project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        applyDto(project, dto);
        project.setUpdatedDate(LocalDate.now());

        return mapToResponse(projectRepository.save(project));
    }

    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    // ===== Mapping helpers =====

    private void applyDto(ProjectEntity project, ProjectRequestDto dto) {
        project.setTitle(dto.getTitle());
        project.setSummary(dto.getSummary());
        project.setDescription(dto.getDescription());
        project.setLink(dto.getLink());
        project.setTechStack(dto.getTechStack());
        }

    private ProjectResponseDto mapToResponse(ProjectEntity project) {
        ProjectResponseDto dto = new ProjectResponseDto();
        dto.setId(project.getId());
        dto.setTitle(project.getTitle());
        dto.setSummary(project.getSummary());
        dto.setDescription(project.getDescription());
        dto.setLink(project.getLink());
        dto.setTechStack(project.getTechStack());

        return dto;
    }
}
