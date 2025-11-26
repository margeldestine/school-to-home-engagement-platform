package com.appdevg5.geeks.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class ParentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int parent_id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

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

    public void viewGrades() {
    }

    public void signForms() {
    }
}
