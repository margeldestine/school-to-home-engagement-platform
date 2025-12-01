package com.appdevg5.geeks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.appdevg5.geeks.entity.StudentEntity;
import com.appdevg5.geeks.service.StudentService;

@RestController
@RequestMapping("/api/student")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    @Autowired
    StudentService sserv;

    @PostMapping("/insertStudentRecord")
    public StudentEntity insertStudentRecord(@RequestBody StudentEntity student) {
        return sserv.insertStudentRecord(student);
    }

    @PostMapping
    public StudentEntity createStudent(@RequestBody StudentEntity student) {
        return sserv.insertStudentRecord(student);
    }

    @GetMapping("/getAllStudents")
    public List<StudentEntity> getAllStudents(){
        return sserv.getAllStudents();
    }

    @GetMapping
    public List<StudentEntity> getStudents(){
        return sserv.getAllStudents();
    }

    @PutMapping("/updateStudent")
    public StudentEntity updateStudent(@RequestParam int studentId, @RequestBody StudentEntity newStudentDetails) {
        return sserv.updateStudent(studentId, newStudentDetails);
    }

    @DeleteMapping("/deleteStudent/{studentId}")
    public String deleteStudent(@PathVariable int studentId) {
        return sserv.deleteStudent(studentId);
    }
}
