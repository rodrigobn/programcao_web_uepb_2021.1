package com.rodrigo.matricula.controller;

import com.rodrigo.matricula.dto.GenericResponseErrorDTO;
import com.rodrigo.matricula.dto.TeacherDTO;
import com.rodrigo.matricula.exceptions.ExistingTeacherInProjectException;
import com.rodrigo.matricula.mapper.MatriculaMapper;
import com.rodrigo.matricula.models.Teacher;
import com.rodrigo.matricula.repository.TeacherRepository;
import com.rodrigo.matricula.services.TeachersServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teachers")
@Api(value = "Teacher")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TeachersServices teachersServices;

    @Autowired
    private MatriculaMapper matriculaMapper;

    @PostMapping
    @ApiOperation(value = "Cria um novo professor")
    public ResponseEntity<?> createTeacher(@RequestBody TeacherDTO teacherDTO) {
        try {
            Teacher teacher = matriculaMapper.convertFromTeacherDTO(teacherDTO);
            return new ResponseEntity<>(teachersServices.createTeacher(teacher), HttpStatus.CREATED);
        } catch (ExistingTeacherInProjectException e) {
            return ResponseEntity.badRequest().body(new GenericResponseErrorDTO(e.getMessage()));
        }
    }

    @GetMapping
    @ApiOperation(value = "Search a list of all teachers")
    public List<Teacher> getTeacher() {
        return teacherRepository.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Search for a teacher by their identifier")
    public Optional<Teacher> getTeacherById(@PathVariable Long id) {
        return teacherRepository.findById(id);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a teacher from their identifier")
    public Optional<Teacher> updateTeacher(@PathVariable("id") Long id, @RequestBody Teacher teacher) {
        return teacherRepository.findById(id)
                .map(tempCoffee -> {
                    tempCoffee.setName(teacher.getName());
                    return teacherRepository.save(tempCoffee);
                });
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletes a teacher from their identifier")
    public void deleteTeacher(@PathVariable Long id) {
        teacherRepository.delete(teacherRepository.findById(id).get());
    }

    @DeleteMapping
    @ApiOperation(value = "Deletes all teacher")
    public void deleteAllTeacher() {teacherRepository.deleteAll();}
}
