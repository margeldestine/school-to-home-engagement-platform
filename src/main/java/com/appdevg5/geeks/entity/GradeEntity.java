package com.appdevg5.geeks.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class GradeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int grade_id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference(value = "student-grades")
    private StudentEntity student;
    private int teacher_id;
    @ManyToOne
    @JoinColumn(name = "subject_id")
    @JsonBackReference(value = "subject-grades")
    private SubjectEntity subject;
    private float grade_value;
    private int grading_period;
    private String assessment_name;
    private Timestamp recorded_at;

    public GradeEntity(){
        super();
    }

    public GradeEntity(int grade_id, StudentEntity student, int teacher_id, SubjectEntity subject,float grade_value, int grading_period, String assessment_name, Timestamp recorded_at){
        this.grade_id = grade_id;
        this.student = student;
        this.teacher_id = teacher_id;
        this.subject = subject;
        this.grade_value = grade_value;
        this.grading_period = grading_period;
        this.assessment_name = assessment_name;
        this.recorded_at = recorded_at;
    }

    public int getGrade_id(){
        return grade_id;
    }

    public void setGrade_id(int grade_id){
        this.grade_id = grade_id;
    }

    public int getStudent_id(){
        return student != null ? student.getStudent_id() : 0;
    }

    public void setStudent_id(int student_id){
        this.student = new StudentEntity();
        this.student.setStudent_id(student_id);
    }

    public int getTeacher_id(){
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id){
        this.teacher_id = teacher_id;
    }

    public int getSubject_id(){
        return subject != null ? subject.getSubject_id() : 0;
    }

    public void setSubject_id(int subject_id){
        this.subject = new SubjectEntity();
        this.subject.setSubject_id(subject_id);
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

    public String getAssessment_name() {
        return assessment_name;
    }

    public void setAssessment_name(String assessment_name) {
        this.assessment_name = assessment_name;
    }

    public Timestamp getRecorded_at(){
        return recorded_at;
    }

    public void setRecorded_at(Timestamp recorded_at){
        this.recorded_at = recorded_at;
    }

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }

    public SubjectEntity getSubject() {
        return subject;
    }

    public void setSubject(SubjectEntity subject) {
        this.subject = subject;
    }
}
