package com.appdevg5.geeks.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class FormEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int form_id;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @JsonBackReference(value = "teacher-forms")
    private TeacherEntity teacher;

    private String title;
    private String details;
    private Timestamp due_date;
    private Timestamp created_at;

    @OneToMany(mappedBy = "form")
    @JsonManagedReference(value = "form-signatures")
    private List<FormSignatureEntity> signatures = new ArrayList<>();

    public FormEntity() { }

    public FormEntity(int form_id, TeacherEntity teacher, String title, Timestamp due_date, Timestamp created_at) {
        this.form_id = form_id;
        this.teacher = teacher;
        this.title = title;
        this.due_date = due_date;
        this.created_at = created_at;
    }

    public int getForm_id() {
        return form_id;
    }

    public void setForm_id(int form_id) {
        this.form_id = form_id;
    }

    public TeacherEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Timestamp getDue_date() {
        return due_date;
    }

    public void setDue_date(Timestamp due_date) {
        this.due_date = due_date;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public List<FormSignatureEntity> getSignatures() {
        return signatures;
    }

    public void setSignatures(List<FormSignatureEntity> signatures) {
        this.signatures = signatures;
    }

    public int getTeacher_id() {
        return teacher != null ? teacher.getTeacher_id() : 0;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher = new TeacherEntity();
        this.teacher.setTeacher_id(teacher_id);
    }
}
