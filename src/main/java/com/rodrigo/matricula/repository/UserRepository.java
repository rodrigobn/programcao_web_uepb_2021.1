package com.rodrigo.matricula.repository;

import com.rodrigo.matricula.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
