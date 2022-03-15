package com.rodrigo.matricula.controller;

import com.rodrigo.matricula.exceptions.ExistingTeacherInProjectException;
import com.rodrigo.matricula.models.Project;
import com.rodrigo.matricula.models.Student;
import com.rodrigo.matricula.repository.ProjectRepository;
import com.rodrigo.matricula.repository.StudentRepository;
import com.rodrigo.matricula.repository.TeacherRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
@Api(value = "Project")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping
    @ApiOperation(value = "Search a list of all projects")
    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Search for a project by their identifier")
    public Optional<Project> getProjectById(@PathVariable Long id) {
        return projectRepository.findById(id);
    }

    @PostMapping
    @ApiOperation(value = "Create a new project")
    public Project createProject(@RequestBody Project project) throws ExistingTeacherInProjectException {
        return projectRepository.save(project);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a project from their identifier")
    public Optional<Project> updateProject(@PathVariable("id") Long id, @RequestBody Project project) {
        return projectRepository.findById(id)
                .map(tempCoffee -> {
                    tempCoffee.setName(project.getName());
                    return projectRepository.save(tempCoffee);
                });
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletes a project from their identifier")
    public void deleteProject(@PathVariable Long id) {
        projectRepository.delete(projectRepository.findById(id).get());
    }

    @DeleteMapping
    @ApiOperation(value = "Deletes all project")
    public void deleteAllProject() {projectRepository.deleteAll();}
}

