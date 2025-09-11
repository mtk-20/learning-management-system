package com.example.learning_management_system.repository;

import com.example.learning_management_system.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<String, Long> {

    Optional<Student> findByEmail(String email);
}
