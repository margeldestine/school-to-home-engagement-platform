package com.appdevg5.geeks.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.sql.Timestamp;

@Entity
public class CommunicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int communication_id;

    private int teacher_id;
    private int admin_id;
    private String title;
    private String content;
    private LocalDateTime event_Date;
    private Timestamp posted_At;

    public CommunicationEntity() { }

    public CommunicationEntity(int communication_id, int teacher_id, int admin_id, String title, String content, LocalDateTime event_Date, Timestamp posted_At) {
        this.communication_id = communication_id;
        this.teacher_id = teacher_id;
        this.admin_id = admin_id;
        this.title = title;
        this.content = content;
        this.event_Date = event_Date;
        this.posted_At = posted_At;
    }

    public int getCommunication_id() {
        return communication_id;
    }

    public void setCommunication_id(int communication_id) {
        this.communication_id = communication_id;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getEvent_Date() {
        return event_Date;
    }

    public void setEvent_Date(LocalDateTime event_Date) {
        this.event_Date = event_Date;
    }

    public Timestamp getPosted_At() {
        return posted_At;
    }

    public void setPosted_At(Timestamp posted_At) {
        this.posted_At = posted_At;
    }

    public void post() {
    }
}
