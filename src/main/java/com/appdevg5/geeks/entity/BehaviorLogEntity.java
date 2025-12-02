package com.appdevg5.geeks.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import java.sql.Date;
import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class BehaviorLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int behavior_id;

    private String type;
    private String description;
    private Date incident_date;
    private Timestamp recorded_at;
    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference(value = "student-behaviorLogs")
    private StudentEntity student;

    public BehaviorLogEntity() {
    }

    public BehaviorLogEntity(int behavior_id, String type, String description, Date incident_date, Timestamp recorded_at) {
        this.behavior_id = behavior_id;
        this.type = type;
        this.description = description;
        this.incident_date = incident_date;
        this.recorded_at = recorded_at;
    }

    public int getBehavior_id() {
        return behavior_id;
    }

    public void setBehavior_id(int behavior_id) {
        this.behavior_id = behavior_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getIncident_date() {
        return incident_date;
    }

    public void setIncident_date(Date incident_date) {
        this.incident_date = incident_date;
    }

    public Timestamp getRecorded_at() {
        return recorded_at;
    }

    public void setRecorded_at(Timestamp recorded_at) {
        this.recorded_at = recorded_at;
    }

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }

    public int getStudent_id() {
        return student != null ? student.getStudent_id() : 0;
    }

    public void setStudent_id(int student_id) {
        this.student = new StudentEntity();
        this.student.setStudent_id(student_id);
    }
}

