package com.appdevg5.geeks.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class GradeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int grade_id;

    private int student_id;
    private int teacher_id;
    private int subject_id;
    private float grade_value;
    private int grading_period;
    private Timestamp recorded_at;

    public GradeEntity(){
        super();
    }

    public GradeEntity(int grade_id, int student_id, int teacher_id, int subject_id,float grade_value, int grading_period, Timestamp recorded_at){
        this.grade_id = grade_id;
        this.student_id = student_id;
        this.teacher_id = teacher_id;
        this.subject_id = subject_id;
        this.grade_value = grade_value;
        this.grading_period = grading_period;
        this.recorded_at = recorded_at;
    }

    public int getGrade_id(){
        return grade_id;
    }

    public void setGrade_id(int grade_id){
        this.grade_id = grade_id;
    }

    public int getStudent_id(){
        return student_id;
    }

    public void setStudent_id(int student_id){
        this.student_id = student_id;
    }

    public int getTeacher_id(){
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id){
        this.teacher_id = teacher_id;
    }

    public int getSubject_id(){
        return subject_id;
    }

    public void setSubject_id(int subject_id){
        this.subject_id = subject_id;
    }

    public float getGrade_value(){
        return grade_value;
    }

    public void setGrade_value(float grade_value){
        this.grade_value = grade_value;
    }

    public int getGrading_period(){
        return grading_period;
    }

    public void setGrading_period(int grading_period){
        this.grading_period = grading_period;
    }

    public Timestamp getRecorded_at(){
        return recorded_at;
    }

    public void setRecorded_at(Timestamp recorded_at){
        this.recorded_at = recorded_at;
    }
}
