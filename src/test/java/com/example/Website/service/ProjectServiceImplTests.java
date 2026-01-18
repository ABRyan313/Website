package com.example.Website.service;

import com.example.Website.domain.DTO.ProjectResponseDto;
import com.example.Website.persistence.entity.ProjectEntity;
import com.example.Website.persistence.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceImplTests {

    @InjectMocks
    private ProjectServiceImpl projectServiceImpl;

    @Mock
    private ProjectRepository projectRepository;

    @Test
    public void getAllProjects_returnsMappedDtosInOrder() {
        ProjectEntity p1 = new ProjectEntity();
        p1.setId(1L);
        p1.setTitle("Title 1");
        p1.setSummary("Summary 1");
        p1.setDescription("Desc 1");
        p1.setLink("Link 1");
        p1.setUpdatedDate(LocalDate.of(2023, 1, 2));

        ProjectEntity p2 = new ProjectEntity();
        p2.setId(2L);
        p2.setTitle("Title 2");
        p2.setSummary("Summary 2");
        p2.setDescription("Desc 2");
        p2.setLink("Link 2");
        p2.setUpdatedDate(LocalDate.of(2023, 1, 3));

        List<ProjectEntity> entities = List.of(p2, p1); // ordered desc by updatedDate
        when(projectRepository.findAllByOrderByUpdatedDateDesc()).thenReturn(entities);

        List<ProjectResponseDto> result = projectServiceImpl.getAllProjects();

        assertEquals(2, result.size());
        assertEquals(p2.getId(), result.get(0).getId());
        assertEquals(p2.getTitle(), result.get(0).getTitle());
        assertEquals(p1.getId(), result.get(1).getId());
        assertEquals(p1.getTitle(), result.get(1).getTitle());
    }

    @Test
    public void createProject_savesAndReturnsMappedDto() {
        com.example.Website.domain.DTO.ProjectRequestDto dto = new com.example.Website.domain.DTO.ProjectRequestDto();
        dto.setTitle("New Title");
        dto.setSummary("New Summary");
        dto.setDescription("New Description");
        dto.setLink("http://example.com");

        when(projectRepository.save(org.mockito.Mockito.any())).thenAnswer(invocation -> {
            ProjectEntity arg = invocation.getArgument(0);
            arg.setId(10L);
            return arg;
        });

        ProjectResponseDto result = projectServiceImpl.createProject(dto);

        assertEquals(10L, result.getId());
        assertEquals(dto.getTitle(), result.getTitle());
        assertEquals(dto.getSummary(), result.getSummary());
        assertEquals(dto.getDescription(), result.getDescription());
        assertEquals(dto.getLink(), result.getLink());
//        assertEquals(java.time.LocalDate.now(), result.getUpdatedDate());
    }
    
    @Test
    public void updateProject_updatesAndReturnsMappedDto() {
        Long id = 1L;
        ProjectEntity existing = new ProjectEntity();
        existing.setId(id);
        existing.setTitle("Old Title");
        existing.setSummary("Old Summary");
        existing.setDescription("Old Description");
        existing.setLink("http://old.example.com");
        existing.setUpdatedDate(LocalDate.of(2020, 1, 1));

        com.example.Website.domain.DTO.ProjectRequestDto dto = new com.example.Website.domain.DTO.ProjectRequestDto();
        dto.setTitle("Updated Title");
        dto.setSummary("Updated Summary");
        dto.setDescription("Updated Description");
        dto.setLink("http://updated.example.com");

        when(projectRepository.findById(id)).thenReturn(java.util.Optional.of(existing));
        when(projectRepository.save(org.mockito.Mockito.any())).thenAnswer(invocation -> invocation.getArgument(0));

        ProjectResponseDto result = projectServiceImpl.updateProject(id, dto);

        assertEquals(id, result.getId());
        assertEquals(dto.getTitle(), result.getTitle());
        assertEquals(dto.getSummary(), result.getSummary());
        assertEquals(dto.getDescription(), result.getDescription());
        assertEquals(dto.getLink(), result.getLink());
//        assertEquals(java.time.LocalDate.now(), result.getUpdatedDate());
    }
}
