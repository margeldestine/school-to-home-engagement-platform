package com.appdevg5.geeks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appdevg5.geeks.entity.GradeEntity;
import com.appdevg5.geeks.service.GradeService;

@RestController
@RequestMapping("/api/grade")
public class GradeController {

    @Autowired
    GradeService gserv;

    @PostMapping("/insertGradeRecord")
    public GradeEntity insertGradeRecord(@RequestBody GradeEntity grade){
        return gserv.insertGradeRecord(grade);
    }

    @GetMapping("/getAllGrades")
    public List <GradeEntity> getAllGrades(){
        return gserv.getAllGrades();
    }

    @PutMapping("/updateGrade")
    public GradeEntity updateGrade(@RequestParam int gid, @RequestBody GradeEntity newGradeDetails){
        return gserv.updateGrade(gid, newGradeDetails);
    }

    @DeleteMapping("/deleteGrade/{gid}")
    public String deleteGrade(@PathVariable int gid){
        return gserv.deleteGrade(gid);
    }

}
