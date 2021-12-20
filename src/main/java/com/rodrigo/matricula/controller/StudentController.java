package com.rodrigo.matricula.controller;

import com.rodrigo.matricula.models.Student;
import com.rodrigo.matricula.repository.StudentRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
@Api(value = "Student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    @ApiOperation(value = "Search a list of all students")
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Search for a student by their identifier")
    public Optional<Student> getStudentById(@PathVariable Long id) {
        return studentRepository.findById(id);
    }

    @PostMapping
    @ApiOperation(value = "Create a new student")
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a student from their identifier")
    public Optional<Student> updateStudent(@PathVariable("id") Long id, @RequestBody Student student) {
        return studentRepository.findById(id)
                .map(tempCoffee -> {
                    tempCoffee.setName(student.getName());
                    return studentRepository.save(tempCoffee);
                });
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletes a student from their identifier")
    public void deleteStudent(@PathVariable Long id) {
        studentRepository.delete(studentRepository.findById(id).get());
    }
}
