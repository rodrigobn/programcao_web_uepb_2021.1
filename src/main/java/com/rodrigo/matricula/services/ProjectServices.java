package com.rodrigo.matricula.services;

import com.rodrigo.matricula.exceptions.ExistingProjectException;
import com.rodrigo.matricula.models.Project;
import com.rodrigo.matricula.repository.ProjectRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServices {

    @Autowired
    private ProjectRepository projectRepository;

    public Project createProject(Project project) throws ExistingProjectException {
        if (projectRepository.findByName(project.getName()).isPresent())
            throw new ExistingProjectException("Já existe esse projeto!");

        return projectRepository.save(project);
    }

    public Project updateProject(Long id, Project project) throws NotFoundException {
        if (!projectRepository.findById(project.getId()).isPresent())
            throw new NotFoundException("Não existe esse projeto");

        project.setId(id);
        return projectRepository.save(project);
    }

    public List<Project> listAllProject() {
        return projectRepository.findAll();
    }

    public Project findById(Long id) throws NotFoundException {
        return projectRepository.findById(id).orElseThrow(() -> new NotFoundException("Não existe um projeto com esse identificador!"));
    }

    public void deleteProject(Long id) {
        Project projectToDelete = projectRepository.findById(id).get();
        projectRepository.delete(projectToDelete);
    }
}
