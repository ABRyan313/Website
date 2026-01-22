package com.example.Website.controller;

import com.example.Website.domain.DTO.ProjectRequestDto;
import com.example.Website.domain.DTO.ProjectResponseDto;
import com.example.Website.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    // GET /api/projects
    @GetMapping
    public ResponseEntity<List<ProjectResponseDto>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping("/{id}")
    public ProjectResponseDto getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }


    // POST /api/projects
    @PostMapping
    public ResponseEntity<ProjectResponseDto> createProject(
            @RequestBody ProjectRequestDto dto
    ) {
        ProjectResponseDto response = projectService.createProject(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // PUT /api/projects/{id}
    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> updateProject(
            @PathVariable Long id,
            @RequestBody ProjectRequestDto dto
    ) {
        ProjectResponseDto response = projectService.updateProject(id, dto);
        return ResponseEntity.ok(response);
    }

    // DELETE /api/projects/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}

