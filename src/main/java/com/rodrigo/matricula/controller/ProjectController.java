package com.rodrigo.matricula.controller;

import com.rodrigo.matricula.dto.GenericResponseErrorDTO;
import com.rodrigo.matricula.exceptions.ExistingProjectException;
import com.rodrigo.matricula.models.Project;
import com.rodrigo.matricula.models.Student;
import com.rodrigo.matricula.repository.ProjectRepository;
import com.rodrigo.matricula.repository.StudentRepository;
import com.rodrigo.matricula.repository.TeacherRepository;
import com.rodrigo.matricula.services.ProjectServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
@Api(value = "Project")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectServices projectServices;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

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
    public ResponseEntity<?> createTeacher(@RequestBody Project project) {
        try {
            return new ResponseEntity<>(projectServices.createProject(project), HttpStatus.CREATED);
        } catch (ExistingProjectException e) {
            return ResponseEntity.badRequest().body(new GenericResponseErrorDTO(e.getMessage()));
        }
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

    @PatchMapping("{teacherId}/{projectId}/registerStudent/{studentId}")
    @ApiOperation(value = "Vincula um aluno a um projeto do professor")
    public Optional<Project> patchClassroom(@PathVariable long teacherId, @PathVariable long projectId, @PathVariable String studentId) {
        if (teacherRepository.findById(teacherId).isPresent()){
            return projectRepository.findById(projectId)
                    .map(tempProject -> {
                        Project project = projectRepository.findById(projectId).get();

                        String[] listIdsStudents = project.getIdsStudents().split(",");
                        String result = "";

                        for (String listIdsStudent : listIdsStudents) {
                            result += listIdsStudent + ",";
                        }
                        result += studentId.toString();
                        tempProject.setIdsStudents(result);

                        projectRepository.delete(tempProject);
                        return projectRepository.save(tempProject);
                    });
            }
        return Optional.of(new Project());
    }

    @GetMapping("/{projectId}/students")
    @ApiOperation(value = "Search for all students in project by their identifier")
    public List<Optional<Student>> getStudentsInProjectById(@PathVariable Long projectId) {
        List<Optional<Student>> students = new ArrayList<>();
        if (projectRepository.findById(projectId).isPresent()){
            Project project = projectRepository.findById(projectId).get();

            String[] listIdsStudents = project.getIdsStudents().split(",");

            for(String idStudent : listIdsStudents){
                students.add(studentRepository.findById(Long.valueOf(idStudent)));
            }
        }

        return students;
    }
}

