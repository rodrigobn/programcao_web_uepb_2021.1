package com.rodrigo.matricula.repository;

import com.rodrigo.matricula.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
