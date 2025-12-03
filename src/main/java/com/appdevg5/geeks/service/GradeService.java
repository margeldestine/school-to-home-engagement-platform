package com.appdevg5.geeks.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdevg5.geeks.entity.GradeEntity;
import com.appdevg5.geeks.repository.GradeRepository;

@Service
public class GradeService {

    @Autowired
    GradeRepository grepo;

    public GradeEntity insertGradeRecord(GradeEntity grade){
        return grepo.save(grade);
    }

    public List<GradeEntity> getAllGrades(){
        return grepo.findAll();
    }
    
    public List<GradeEntity> getGradesBySection(int sectionId){
        return grepo.findBySection(sectionId);
    }
    
    public List<GradeEntity> getGradesByStudent(int studentId){
        return grepo.findByStudentId(studentId); // Updated method name
    }

    public GradeEntity updateGrade(int gid, GradeEntity newGradeDetails){
        GradeEntity grade = grepo.findById(gid).orElseThrow(
            () -> new NoSuchElementException("Grade " + gid + " does not exist!")
        );

        grade.setTeacher_id(newGradeDetails.getTeacher_id());
        grade.setSubject_id(newGradeDetails.getSubject_id());
        grade.setGrade_value(newGradeDetails.getGrade_value());
        grade.setGrading_period(newGradeDetails.getGrading_period());
        grade.setRecorded_at(newGradeDetails.getRecorded_at());

        return grepo.save(grade);
    }

    public String deleteGrade(int gid){
        String msg = "";

        if(grepo.findById(gid).orElse(null) != null){
            grepo.deleteById(gid);
            msg = "Grade " + gid + " successfully deleted!";
        }else{
            msg = "Grade " + gid + " does not exist!";
        }

        return msg;
    }
}