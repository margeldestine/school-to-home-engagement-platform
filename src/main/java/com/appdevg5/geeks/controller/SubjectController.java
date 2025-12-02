package com.appdevg5.geeks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.appdevg5.geeks.entity.SubjectEntity;
import com.appdevg5.geeks.service.SubjectService;

@RestController
@RequestMapping("/api/subjects")
@CrossOrigin(origins = "http://localhost:3000")
public class SubjectController {

    @Autowired
    SubjectService sserv;

    @PostMapping("/insertSubjectRecord")
    public SubjectEntity insertSubjectRecord(@RequestBody SubjectEntity subject) {
        return sserv.insertSubjectRecord(subject);
    }

    @PostMapping
    public SubjectEntity createSubject(@RequestBody SubjectEntity subject) {
        return sserv.insertSubjectRecord(subject);
    }

    @GetMapping("/getAllSubjects")
    public List<SubjectEntity> getAllSubjects() {
        return sserv.getAllSubjects();
    }

    @GetMapping
    public List<SubjectEntity> getSubjects() {
        return sserv.getAllSubjects();
    }

    @PutMapping("/updateSubject")
    public SubjectEntity updateSubject(@RequestParam int subjectId, @RequestBody SubjectEntity newSubjectDetails) {
        return sserv.updateSubject(subjectId, newSubjectDetails);
    }

    @DeleteMapping("/deleteSubject/{subjectId}")
    public String deleteSubject(@PathVariable int subjectId) {
        return sserv.deleteSubject(subjectId);
    }
}

