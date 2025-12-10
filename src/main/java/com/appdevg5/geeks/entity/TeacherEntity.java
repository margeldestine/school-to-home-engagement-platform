package com.appdevg5.geeks.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class TeacherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teacher_id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "teacher")
    @JsonManagedReference(value = "teacher-communications")
    private List<CommunicationEntity> communications = new ArrayList<>();

    @OneToMany(mappedBy = "teacher")
    @JsonManagedReference(value = "teacher-forms")
    private List<FormEntity> forms = new ArrayList<>();

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

    public List<CommunicationEntity> getCommunications() {
        return communications;
    }

    public void setCommunications(List<CommunicationEntity> communications) {
        this.communications = communications;
    }

    public List<FormEntity> getForms() {
        return forms;
    }

    public void setForms(List<FormEntity> forms) {
        this.forms = forms;
    }

    public void recordedGrade() {
    }

    public void recordedAttendance() {
    }

    public void createForm() {
    }
}