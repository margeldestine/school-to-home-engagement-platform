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
public class AttendanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int attendance_id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference(value = "student-attendance")
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @JsonBackReference(value = "teacher-attendance")
    private TeacherEntity teacher;
    private Timestamp attendance_date;
    private String status;

    public AttendanceEntity(){
        super();
    }

    public AttendanceEntity(int attendance_id, StudentEntity student, TeacherEntity teacher, Timestamp attendance_date, String status){
        this.attendance_id = attendance_id;
        this.student = student;
        this.teacher = teacher;
        this.attendance_date = attendance_date;
        this.status = status;
    }

    public int getAttendance_id(){
        return attendance_id;
    }

    public void setAttendance_id(int attendance_id){
        this.attendance_id = attendance_id;
    }

    public int getStudent_id(){
        return student != null ? student.getStudent_id() : 0;
    }

    public void setStudent_id(int student_id){
        this.student = new StudentEntity();
        this.student.setStudent_id(student_id);
    }

    public int getTeacher_id(){
        return teacher != null ? teacher.getTeacher_id() : 0;
    }

    public void setTeacher_id(int teacher_id){
        this.teacher = new TeacherEntity();
        this.teacher.setTeacher_id(teacher_id);
    }

    public Timestamp getAttendance_date(){
        return attendance_date;
    }

    public void setAttendance_date(Timestamp attendance_date){
        this.attendance_date = attendance_date;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }

    public TeacherEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
    }

}
