package com.appdevg5.geeks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import com.appdevg5.geeks.entity.BehaviorLogEntity;
import com.appdevg5.geeks.service.BehaviorLogService;

@RestController
@RequestMapping("/api/behavior-logs")
@CrossOrigin(origins = "http://localhost:3000")
public class BehaviorLogController {

    @Autowired
    BehaviorLogService bserv;

    @PostMapping("/insertBehaviorLogRecord")
    public BehaviorLogEntity insertBehaviorLogRecord(@RequestBody BehaviorLogEntity behaviorLog) {
        return bserv.insertBehaviorLogRecord(behaviorLog);
    }

    @PostMapping
    public BehaviorLogEntity createBehaviorLog(@RequestBody Map<String, Object> payload) {
        int studentId = (Integer) payload.get("student_id");
        String incidentDate = (String) payload.get("incident_date");
        String description = (String) payload.get("description");
        String type = (String) payload.get("type");
        String recordedAt = (String) payload.get("recorded_at");
        
        BehaviorLogEntity behaviorLog = new BehaviorLogEntity();
        behaviorLog.setStudent_id(studentId);
        behaviorLog.setIncident_date(Date.valueOf(incidentDate));
        behaviorLog.setDescription(description);
        behaviorLog.setType(type);
        behaviorLog.setRecorded_at(Timestamp.valueOf(recordedAt));
        
        return bserv.insertBehaviorLogRecord(behaviorLog);
    }

    @GetMapping("/getAllBehaviorLogs")
    public List<BehaviorLogEntity> getAllBehaviorLogs() {
        return bserv.getAllBehaviorLogs();
    }

    @GetMapping
    public List<BehaviorLogEntity> getBehaviorLogs() {
        return bserv.getAllBehaviorLogs();
    }

    @PutMapping("/updateBehaviorLog")
    public BehaviorLogEntity updateBehaviorLog(@RequestParam int behaviorId, @RequestBody BehaviorLogEntity newBehaviorLogDetails) {
        return bserv.updateBehaviorLog(behaviorId, newBehaviorLogDetails);
    }

    @DeleteMapping("/deleteBehaviorLog/{behaviorId}")
    public String deleteBehaviorLog(@PathVariable int behaviorId) {
        return bserv.deleteBehaviorLog(behaviorId);
    }
}