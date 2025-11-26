package com.appdevg5.geeks.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
public class BehaviorLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int behavior_id;

    private String type;
    private String description;
    private Date incident_date;
    private Timestamp recorded_at;

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
}

