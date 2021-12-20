package com.rodrigo.matricula.repository;

import com.rodrigo.matricula.models.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
}
