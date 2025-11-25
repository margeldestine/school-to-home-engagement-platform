package com.appdevg5.geeks.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class TeacherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teacher_id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public TeacherEntity() {
    }

    public TeacherEntity(int teacher_id, UserEntity user) {
        this.teacher_id = teacher_id;
        this.user = user;
    }

    public int getTeacher_id() {
        return teacher_id;
    }
    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public UserEntity getUser() {
        return user;
    }
    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void recordedGrade() {
    }

    public void recordedAttendance() {
    }

    public void createForm() {
    }
}
