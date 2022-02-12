package com.rodrigo.matricula.controller;

import com.rodrigo.matricula.models.Teacher;
import com.rodrigo.matricula.repository.TeacherRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teachers")
@Api(value = "Teacher")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

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

    @PostMapping
    @ApiOperation(value = "Create a new teacher")
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return teacherRepository.save(teacher);
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
