package com.rodrigo.matricula.controller;

import com.google.gson.Gson;
import com.rodrigo.matricula.models.Classroom;
import com.rodrigo.matricula.repository.ClassroomRepository;
import com.rodrigo.matricula.repository.StudentRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/classrooms")
@Api(value = "Classroom")
public class ClassroomController {

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    @ApiOperation(value = "Search a list of all classrooms")
    public List<Classroom> getClassroom() {
        return classroomRepository.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Search for a classroom by their identifier")
    public Optional<Classroom> getClassroomById(@PathVariable Long id) {
        return classroomRepository.findById(id);
    }

    @PostMapping
    @ApiOperation(value = "Create a new classroom")
    public Classroom createClassroom(@RequestBody Classroom classroom) {
        return classroomRepository.save(classroom);

    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a classroom from their identifier")
    public Optional<Classroom> updateClassroom(@PathVariable("id") Long id, @RequestBody Classroom classroom) {
        return classroomRepository.findById(id)
                .map(tempClassroom -> {
                    if (classroom.getName() != null){
                        tempClassroom.setName(classroom.getName());
                    }
                    if (classroom.getRoom() != null){
                        tempClassroom.setRoom(classroom.getRoom());
                    }
                    if (classroom.getIdsStudents() != null){
                        tempClassroom.setIdsStudents(classroom.getIdsStudents());
                    }
                    if (classroom.getIdTeacher() != null){
                        tempClassroom.setIdTeacher(classroom.getIdTeacher());
                    }
                    classroomRepository.delete(tempClassroom);
                    return classroomRepository.save(tempClassroom);
                });
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletes a classroom from their identifier")
    public void deleteClassroom(@PathVariable Long id) {
        classroomRepository.delete(classroomRepository.findById(id).get());
    }

    @DeleteMapping()
    @ApiOperation(value = "Deletes all classroom")
    public void deleteAllClassroom() {classroomRepository.deleteAll();}

    @PatchMapping("/{classroomId}/registerStudent/{studentId}")
    @ApiOperation(value = "Links a student to a teacher")
    public Optional<Classroom> patchClassroom(@PathVariable long classroomId, @PathVariable String studentId) {
        return classroomRepository.findById(classroomId)
                .map(tempClassroom -> {
                    Classroom classroom = classroomRepository.findById(classroomId).get();

                    String[] listIdsStudents = classroom.getIdsStudents().split(",");
                    String result = "";

                    for (String listIdsStudent : listIdsStudents) {
                        result += listIdsStudent + ",";
                    }
                    result += studentId.toString();
                    tempClassroom.setIdsStudents(result);

                    classroomRepository.delete(tempClassroom);
                    return classroomRepository.save(tempClassroom);
                });
    }
}
