package com.example.student.repository;

import com.example.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {

    Optional<Student> findByStudentCode(String studentCode);

    Optional<Student> findByEmail(String email);

    List<Student> findByFullNameContainingIgnoreCase(String fullName);

    List<Student> findByClassName(String className);
}