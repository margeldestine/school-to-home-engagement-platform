package com.appdevg5.geeks.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int student_id;

    private int section_id;
    private int student_number;
    private String first_name;
    private String last_name;
    private int grade_level;

    public StudentEntity() { }

    public StudentEntity(int student_id, int section_id, int student_number, String first_name, String last_name, int grade_level) {
        this.student_id = student_id;
        this.section_id = section_id;
        this.student_number = student_number;
        this.first_name = first_name;
        this.last_name = last_name;
        this.grade_level = grade_level;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    
    public int getSection_id() {
        return section_id;
    }

    public void setSection_id(int section_id) {
        this.section_id = section_id;
    }

    public int getStudent_number() {
        return student_number;
    }

    public void setStudent_number(int student_number) {
        this.student_number = student_number;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getGrade_level() {
        return grade_level;
    }

    public void setGrade_level(int grade_level) {
        this.grade_level = grade_level;
    }

    public void getProfile() {
    }

    public void updateInfo() {   
    }
}
