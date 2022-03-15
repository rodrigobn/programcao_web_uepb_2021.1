package com.rodrigo.matricula.repository;

import com.rodrigo.matricula.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
