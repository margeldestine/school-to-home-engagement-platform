package com.appdevg5.geeks.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int student_id;

    @ManyToOne
    @JoinColumn(name = "section_id")
    @JsonBackReference(value = "section-students")
    private SectionEntity section;
    private int student_number;
    private String first_name;
    private String last_name;
    private int grade_level;

    @OneToMany(mappedBy = "student")
    @JsonManagedReference(value = "student-attendance")
    private List<AttendanceEntity> attendances = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    @JsonManagedReference(value = "student-grades")
    private List<GradeEntity> grades = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    @JsonManagedReference(value = "student-behaviorLogs")
    private List<BehaviorLogEntity> behaviorLogs = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    @JsonManagedReference(value = "student-signatures")
    private List<FormSignatureEntity> signatures = new ArrayList<>();

    public StudentEntity() { }

    public StudentEntity(int student_id, SectionEntity section, int student_number, String first_name, String last_name, int grade_level) {
        this.student_id = student_id;
        this.section = section;
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
        return section != null ? section.getSection_id() : 0;
    }

    public void setSection_id(int section_id) {
        this.section = new SectionEntity(section_id);
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

    public SectionEntity getSection() {
        return section;
    }

    public void setSection(SectionEntity section) {
        this.section = section;
    }

    public List<AttendanceEntity> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<AttendanceEntity> attendances) {
        this.attendances = attendances;
    }

    public List<GradeEntity> getGrades() {
        return grades;
    }

    public void setGrades(List<GradeEntity> grades) {
        this.grades = grades;
    }

    public List<BehaviorLogEntity> getBehaviorLogs() {
        return behaviorLogs;
    }

    public void setBehaviorLogs(List<BehaviorLogEntity> behaviorLogs) {
        this.behaviorLogs = behaviorLogs;
    }

    public List<FormSignatureEntity> getSignatures() {
        return signatures;
    }

    public void setSignatures(List<FormSignatureEntity> signatures) {
        this.signatures = signatures;
    }

    public void getProfile() {
    }

    public void updateInfo() {   
    }
}
