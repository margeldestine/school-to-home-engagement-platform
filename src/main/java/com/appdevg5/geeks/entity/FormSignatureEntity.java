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
public class FormSignatureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int signature_id;

    @ManyToOne
    @JoinColumn(name = "form_id")
    @JsonBackReference(value = "form-signatures")
    private FormEntity form;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonBackReference(value = "parent-signatures")
    private ParentEntity parent;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference(value = "student-signatures")
    private StudentEntity student;
    private Timestamp signed_at;

    public FormSignatureEntity(){
        super();
    }

    public FormSignatureEntity(int signature_id, FormEntity form, ParentEntity parent, StudentEntity student, Timestamp signed_at){
        this.signature_id = signature_id;
        this.form = form;
        this.parent = parent;
        this.student = student;
        this.signed_at = signed_at;
    }

    public int getSignature_id(){
        return signature_id;
    }

    public void setSignature_id(int signature_id){
        this.signature_id = signature_id;
    }

    public int getForm_id(){
        return form != null ? form.getForm_id() : 0;
    }

    public void setForm_id(int form_id){
        this.form = new FormEntity();
        this.form.setForm_id(form_id);
    }

    public int getParent_id(){
        return parent != null ? parent.getParent_id() : 0;
    }

    public void setParent_id(int parent_id){
        this.parent = new ParentEntity();
        this.parent.setParent_id(parent_id);
    }

    public int getStudent_id(){
        return student != null ? student.getStudent_id() : 0;
    }

    public void setStudent_id(int student_id){
        this.student = new StudentEntity();
        this.student.setStudent_id(student_id);
    }

    public Timestamp getSigned_at(){
        return signed_at;
    }

    public void setSigned_at(Timestamp signed_at){
        this.signed_at = signed_at;
    }

    // verifySignature(): boolean — optional method from diagram
    public boolean verifySignature(){
        return signed_at != null;
    }

    public FormEntity getForm() {
        return form;
    }

    public void setForm(FormEntity form) {
        this.form = form;
    }

    public ParentEntity getParent() {
        return parent;
    }

    public void setParent(ParentEntity parent) {
        this.parent = parent;
    }

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }
}
