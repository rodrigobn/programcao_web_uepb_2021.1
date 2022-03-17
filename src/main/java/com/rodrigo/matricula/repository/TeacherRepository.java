package com.rodrigo.matricula.repository;

import com.rodrigo.matricula.models.Student;
import com.rodrigo.matricula.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findByName(String name);
}
