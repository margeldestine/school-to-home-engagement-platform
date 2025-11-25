package com.appdevg5.geeks.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AttendanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int attendance_id;

    private int student_id;
    private int teacher_id;
    private Timestamp attendance_date;
    private String status;

    public AttendanceEntity(){
        super();
    }

    public AttendanceEntity(int attendance_id, int student_id, int teacher_id, Timestamp attendance_date, String status){
        this.attendance_id = attendance_id;
        this.student_id = student_id;
        this.teacher_id = teacher_id;
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

}
