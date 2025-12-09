package com.appdevg5.geeks.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class ParentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int parent_id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentEntity student;

    @OneToMany(mappedBy = "parent")
    @JsonManagedReference(value = "parent-signatures")
    private List<FormSignatureEntity> signatures = new ArrayList<>();

    public ParentEntity() { }

    public ParentEntity(int parent_id, UserEntity user) {
        this.parent_id = parent_id;
        this.user = user;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<FormSignatureEntity> getSignatures() {
        return signatures;
    }

    public void setSignatures(List<FormSignatureEntity> signatures) {
        this.signatures = signatures;
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

    public void viewGrades() {
    }

    public void signForms() {
    }
}
