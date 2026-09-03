package com.example.student.controller;

import com.example.student.entity.Student;
import com.example.student.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/students")
// @CrossOrigin("*")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // GET /api/students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {

        return ResponseEntity.ok(
                studentService.getAllStudents()
        );
    }

    // GET /api/students/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                studentService.getStudentById(id)
        );
    }

    // GET /api/students/code/{code}
    @GetMapping("/code/{code}")
    public ResponseEntity<Student> getStudentByCode(
            @PathVariable String code) {

        return ResponseEntity.ok(
                studentService.getStudentByCode(code)
        );
    }

    // GET /api/students/search?keyword=Nguyen
    @GetMapping("/search")
    public ResponseEntity<List<Student>> searchStudents(
            @RequestParam String keyword) {

        return ResponseEntity.ok(
                studentService.searchStudents(keyword)
        );
    }

    // POST /api/students
    @PostMapping
    public ResponseEntity<Student> createStudent(
            @RequestBody Student student) {

        Student newStudent =
                studentService.createStudent(student);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newStudent);
    }

    // PUT /api/students/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable UUID id,
            @RequestBody Student student) {

        return ResponseEntity.ok(
                studentService.updateStudent(id, student)
        );
    }

    // DELETE /api/students/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(
            @PathVariable UUID id) {

        studentService.deleteStudent(id);

        return ResponseEntity.noContent().build();
    }

    // DELETE /api/students
    @DeleteMapping
    public ResponseEntity<Void> deleteAllStudents() {

        studentService.deleteAllStudents();

        return ResponseEntity.noContent().build();
    }
}