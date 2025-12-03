package com.appdevg5.geeks.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import com.appdevg5.geeks.repository.StudentRepository;
import com.appdevg5.geeks.entity.StudentEntity;
import com.appdevg5.geeks.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    private final StudentRepository studentRepository;
    
    @Autowired
    private StudentService studentService;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/by-number/{studentNumber}")
    public ResponseEntity<StudentEntity> getByStudentNumber(@PathVariable String studentNumber) {
        return studentRepository.findByStudentNumber(studentNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping
    public List<StudentEntity> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/by-section/{sectionId}")
    public List<StudentEntity> getStudentsBySection(@PathVariable int sectionId) {
        return studentService.getStudentsBySection(sectionId);
    }
}
