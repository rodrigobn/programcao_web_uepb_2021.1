package com.rodrigo.matricula.mapper;

import com.rodrigo.matricula.dto.StudentDTO;
import com.rodrigo.matricula.dto.TeacherDTO;
import com.rodrigo.matricula.dto.UserDTO;
import com.rodrigo.matricula.models.Student;
import com.rodrigo.matricula.models.Teacher;
import com.rodrigo.matricula.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

public class MatriculaMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Bean
    public UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return userDTO;
    }

    @Bean
    public User convertFromUserDTO(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);

        return user;
    }

    @Bean
    public TeacherDTO convertToTeacherDTO(Teacher teacher) {
        TeacherDTO teacherDTO = modelMapper.map(teacher, TeacherDTO.class);

        return teacherDTO;
    }

    @Bean
    public Teacher convertFromTeacherDTO(TeacherDTO teacherDTO) {
        Teacher teacher = modelMapper.map(teacherDTO, Teacher.class);

        return teacher;
    }

    @Bean
    public StudentDTO convertToStudentDTO(Optional<Student> student) {
        StudentDTO studentDTO = modelMapper.map(student, StudentDTO.class);

        return studentDTO;
    }

    @Bean
    public Student convertFromStudentDTO(StudentDTO studentDTO) {
        Student student = modelMapper.map(studentDTO, Student.class);

        return student;
    }
}
