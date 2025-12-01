package com.appdevg5.geeks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.appdevg5.geeks.entity.TeacherEntity;
import com.appdevg5.geeks.service.TeacherService;

@RestController
@RequestMapping("/api/teacher")
@CrossOrigin(origins = "http://localhost:3000")
public class TeacherController {

    @Autowired
    TeacherService tserv;

    @PostMapping("/insertTeacherRecord")
    public TeacherEntity insertTeacherRecord(@RequestBody TeacherEntity teacher) {
        return tserv.insertTeacherRecord(teacher);
    }

    @PostMapping
    public TeacherEntity createTeacher(@RequestBody TeacherEntity teacher) {
        return tserv.insertTeacherRecord(teacher);
    }

    @GetMapping("/getAllTeachers")
    public List<TeacherEntity> getAllTeachers() {
        return tserv.getAllTeachers();
    }

    @GetMapping
    public List<TeacherEntity> getTeachers() {
        return tserv.getAllTeachers();
    }

    @PutMapping("/updateTeacher")
    public TeacherEntity updateTeacher(@RequestParam int tid, @RequestBody TeacherEntity newTeacherDetails) {
        return tserv.updateTeacher(tid, newTeacherDetails);
    }

    @DeleteMapping("/deleteTeacher/{tid}")
    public String deleteTeacher(@PathVariable int tid) {
        return tserv.deleteTeacher(tid);
    }
}
