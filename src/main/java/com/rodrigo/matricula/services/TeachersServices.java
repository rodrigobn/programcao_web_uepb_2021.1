package com.rodrigo.matricula.services;

import com.rodrigo.matricula.exceptions.ExistingTeacherInProjectException;
import com.rodrigo.matricula.models.Teacher;
import com.rodrigo.matricula.repository.TeacherRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeachersServices {

    @Autowired
    private TeacherRepository teacherRepository;

    public Teacher createTeacher(Teacher teacher) throws ExistingTeacherInProjectException {
        if (teacherRepository.findByName(teacher.getName()).isPresent())
            throw new ExistingTeacherInProjectException("Já existe esse professor!");
        return teacherRepository.save(teacher);
    }

    public Teacher updateTeacher(Long id, Teacher teacher) throws NotFoundException {
        if (!teacherRepository.findById(teacher.getId()).isPresent())
            throw new NotFoundException("Não existe esse professor");

        teacher.setId(id);
        return teacherRepository.save(teacher);
    }

    public List<Teacher> listAllTeacher() {
        return teacherRepository.findAll();
    }

    public Teacher findById(Long id) throws NotFoundException {
        return teacherRepository.findById(id).orElseThrow(() -> new NotFoundException("Não existe um professor com esse identificador!"));
    }

    public void deleteTeacher(Long id) {
        Teacher teacherToDelete = teacherRepository.findById(id).get();
        teacherRepository.delete(teacherToDelete);
    }
}
