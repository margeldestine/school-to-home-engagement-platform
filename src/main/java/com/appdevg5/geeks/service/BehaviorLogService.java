package com.appdevg5.geeks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.List;
import java.util.NoSuchElementException;
import com.appdevg5.geeks.entity.BehaviorLogEntity;
import com.appdevg5.geeks.entity.StudentEntity;
import com.appdevg5.geeks.repository.BehaviorLogRepository;
import com.appdevg5.geeks.repository.StudentRepository;

@Service
public class BehaviorLogService {

    @Autowired
    BehaviorLogRepository brepo;

    @Autowired
    StudentRepository srepo;

    /**
     * Insert a new behavior log record
     */
    public BehaviorLogEntity insertBehaviorLogRecord(BehaviorLogEntity behaviorLog) {
        // Handle student relationship
        Integer studentId = behaviorLog.getStudent_id();
        if (studentId != null && studentId > 0) {
            StudentEntity student = srepo.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException("Student " + studentId + " not found"));
            behaviorLog.setStudent(student);
        } else if (behaviorLog.getStudent() != null) {
            Integer studentObjId = behaviorLog.getStudent().getStudent_id();
            if (studentObjId != null && studentObjId > 0) {
                StudentEntity student = srepo.findById(studentObjId)
                    .orElseThrow(() -> new NoSuchElementException("Student " + studentObjId + " not found"));
                behaviorLog.setStudent(student);
            }
        }
        
        // Set timestamp if not already set
        if (behaviorLog.getRecorded_at() == null) {
            behaviorLog.setRecorded_at(new Timestamp(System.currentTimeMillis()));
        }
        
        return brepo.save(behaviorLog);
    }

    /**
     * Get all behavior logs
     */
    public List<BehaviorLogEntity> getAllBehaviorLogs() {
        return brepo.findAll();
    }

    /**
     * Update an existing behavior log
     */
    public BehaviorLogEntity updateBehaviorLog(int behaviorId, BehaviorLogEntity newBehaviorLogDetails) {
        // Find existing behavior log
        BehaviorLogEntity behaviorLog = brepo.findById(behaviorId)
            .orElseThrow(() -> new NoSuchElementException("BehaviorLog " + behaviorId + " does not exist"));

        // Update student if provided
        Integer studentId = newBehaviorLogDetails.getStudent_id();
        if (studentId != null && studentId > 0) {
            StudentEntity student = srepo.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException("Student " + studentId + " not found"));
            behaviorLog.setStudent(student);
        }

        // Update incident date if provided
        if (newBehaviorLogDetails.getIncident_date() != null) {
            behaviorLog.setIncident_date(newBehaviorLogDetails.getIncident_date());
        }

        // Update description if provided
        if (newBehaviorLogDetails.getDescription() != null) {
            behaviorLog.setDescription(newBehaviorLogDetails.getDescription());
        }

        // Update type if provided
        if (newBehaviorLogDetails.getType() != null) {
            behaviorLog.setType(newBehaviorLogDetails.getType());
        }

        // Update recorded_at if provided, otherwise keep existing
        if (newBehaviorLogDetails.getRecorded_at() != null) {
            behaviorLog.setRecorded_at(newBehaviorLogDetails.getRecorded_at());
        }

        return brepo.save(behaviorLog);
    }

    /**
     * Delete a behavior log by ID
     */
    public String deleteBehaviorLog(int behaviorId) {
        if (brepo.existsById(behaviorId)) {
            brepo.deleteById(behaviorId);
            return "BehaviorLog " + behaviorId + " is successfully deleted!";
        } else {
            return "BehaviorLog " + behaviorId + " does not exist.";
        }
    }
}