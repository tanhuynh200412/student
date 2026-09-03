package com.example.student.service;

import com.example.student.entity.Student;
import com.example.student.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(UUID id) {
        return studentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy sinh viên"));
    }

    public Student getStudentByCode(String code) {
        return studentRepository.findByStudentCode(code)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Không tìm thấy sinh viên có mã: " + code));
    }

    public List<Student> searchStudents(String keyword) {
        return studentRepository
                .findByFullNameContainingIgnoreCase(keyword);
    }

    public Student createStudent(Student student) {

        // Sinh ID ở Java
        student.setId(UUID.randomUUID());

        return studentRepository.save(student);
    }

    public Student updateStudent(UUID id, Student student) {

        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Không tìm thấy sinh viên"));

        existingStudent.setStudentCode(
                student.getStudentCode());

        existingStudent.setFullName(
                student.getFullName());

        existingStudent.setEmail(
                student.getEmail());

        existingStudent.setPhone(
                student.getPhone());

        existingStudent.setClassName(
                student.getClassName());

        return studentRepository.save(existingStudent);
    }

    public void deleteStudent(UUID id) {

        if (!studentRepository.existsById(id)) {
            throw new RuntimeException(
                    "Không tìm thấy sinh viên");
        }

        studentRepository.deleteById(id);
    }

    public void deleteAllStudents() {
        studentRepository.deleteAll();
    }
}