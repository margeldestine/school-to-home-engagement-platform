package com.appdevg5.geeks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public BehaviorLogEntity insertBehaviorLogRecord(BehaviorLogEntity behaviorLog) {
        if (behaviorLog.getStudent() != null && behaviorLog.getStudent().getStudent_id() > 0) {
            StudentEntity student = srepo.findById(behaviorLog.getStudent().getStudent_id())
                .orElseThrow(() -> new NoSuchElementException("Student not found"));
            behaviorLog.setStudent(student);
        }
        
        return brepo.save(behaviorLog);
    }

    public List<BehaviorLogEntity> getAllBehaviorLogs() {
        return brepo.findAll();
    }

    public BehaviorLogEntity updateBehaviorLog(int behaviorId, BehaviorLogEntity newBehaviorLogDetails) {
        BehaviorLogEntity behaviorLog = brepo.findById(behaviorId)
            .orElseThrow(() -> new NoSuchElementException("BehaviorLog " + behaviorId + " does not exist"));

        if (newBehaviorLogDetails.getStudent_id() > 0) {
            StudentEntity student = srepo.findById(newBehaviorLogDetails.getStudent_id())
                .orElseThrow(() -> new NoSuchElementException("Student not found"));
            behaviorLog.setStudent(student);
        }

        behaviorLog.setType(newBehaviorLogDetails.getType());
        behaviorLog.setDescription(newBehaviorLogDetails.getDescription());
        behaviorLog.setIncident_date(newBehaviorLogDetails.getIncident_date());
        behaviorLog.setRecorded_at(newBehaviorLogDetails.getRecorded_at());

        return brepo.save(behaviorLog);
    }

    public String deleteBehaviorLog(int behaviorId) {
        if (brepo.existsById(behaviorId)) {
            brepo.deleteById(behaviorId);
            return "BehaviorLog " + behaviorId + " is successfully deleted!";
        } else {
            return "BehaviorLog " + behaviorId + " does not exist.";
        }
    }
}