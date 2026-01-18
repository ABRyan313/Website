package com.example.Website.persistence.repository;

import com.example.Website.persistence.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
    List<ProjectEntity> findAllByOrderByUpdatedDateDesc();
}
