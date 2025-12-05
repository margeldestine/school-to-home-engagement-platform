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

    // CREATE - Main endpoint
    @PostMapping
    public BehaviorLogEntity createBehaviorLog(@RequestBody Map<String, Object> payload) {
        Integer studentId = extractInteger(payload, "studentId", "student_id");
        String incidentDate = extractString(payload, "date", "incident_date");
        String description = extractString(payload, "incident", "description");
        String type = extractString(payload, "actionTaken", "type");
        String recordedAt = (String) payload.get("recorded_at");

        BehaviorLogEntity behaviorLog = new BehaviorLogEntity();
        
        if (studentId != null) {
            behaviorLog.setStudent_id(studentId);
        }
        
        if (incidentDate != null && !incidentDate.isEmpty()) {
            behaviorLog.setIncident_date(parseDate(incidentDate));
        }
        
        behaviorLog.setDescription(description);
        behaviorLog.setType(type);
        
        if (recordedAt != null && !recordedAt.isEmpty()) {
            behaviorLog.setRecorded_at(Timestamp.valueOf(recordedAt));
        } else {
            behaviorLog.setRecorded_at(new Timestamp(System.currentTimeMillis()));
        }
        
        return bserv.insertBehaviorLogRecord(behaviorLog);
    }

    // READ - Get all behavior logs
    @GetMapping
    public List<BehaviorLogEntity> getBehaviorLogs() {
        return bserv.getAllBehaviorLogs();
    }

    // READ - Get all with formatted response
    @GetMapping("/formatted")
    public List<Map<String, Object>> getBehaviorLogsFormatted() {
        List<BehaviorLogEntity> list = bserv.getAllBehaviorLogs();
        return list.stream().map(b -> {
            Map<String, Object> m = new java.util.LinkedHashMap<>();
            m.put("id", b.getBehavior_id());
            m.put("studentId", b.getStudent_id());
            
            String fullName = null;
            if (b.getStudent() != null) {
                String fn = b.getStudent().getFirst_name();
                String ln = b.getStudent().getLast_name();
                fullName = ((fn != null ? fn : "") + " " + (ln != null ? ln : "")).trim();
            }
            m.put("studentName", fullName);
            m.put("date", b.getIncident_date() != null ? b.getIncident_date().toString() : null);
            m.put("incident", b.getDescription());
            m.put("actionTaken", b.getType());
            m.put("recorded_at", b.getRecorded_at() != null ? b.getRecorded_at().toString() : null);
            
            return m;
        }).toList();
    }

    // UPDATE - Update behavior log
    @PutMapping("/{id}")
    public BehaviorLogEntity updateBehaviorLog(@PathVariable("id") int id, @RequestBody Map<String, Object> payload) {
        BehaviorLogEntity update = new BehaviorLogEntity();
        
        // Extract student_id
        Integer studentId = extractInteger(payload, "studentId", "student_id");
        if (studentId != null) {
            update.setStudent_id(studentId);
        }
        
        // Extract incident date
        String incidentDate = extractString(payload, "date", "incident_date");
        if (incidentDate != null && !incidentDate.isEmpty()) {
            update.setIncident_date(parseDate(incidentDate));
        }
        
        // Extract description
        String description = extractString(payload, "incident", "description");
        update.setDescription(description);
        
        // Extract type
        String type = extractString(payload, "actionTaken", "type");
        update.setType(type);
        
        // Extract recorded_at if provided
        String recordedAt = (String) payload.get("recorded_at");
        if (recordedAt != null && !recordedAt.isEmpty()) {
            update.setRecorded_at(Timestamp.valueOf(recordedAt));
        }
        
        return bserv.updateBehaviorLog(id, update);
    }

    // DELETE - Delete behavior log
    @DeleteMapping("/{id}")
    public String deleteBehaviorLog(@PathVariable("id") int id) {
        return bserv.deleteBehaviorLog(id);
    }

    // Legacy endpoints for backward compatibility
    @PostMapping("/insertBehaviorLogRecord")
    public BehaviorLogEntity insertBehaviorLogRecord(@RequestBody BehaviorLogEntity behaviorLog) {
        return bserv.insertBehaviorLogRecord(behaviorLog);
    }

    @GetMapping("/getAllBehaviorLogs")
    public List<BehaviorLogEntity> getAllBehaviorLogs() {
        return bserv.getAllBehaviorLogs();
    }

    @PutMapping("/updateBehaviorLog")
    public BehaviorLogEntity updateBehaviorLogLegacy(@RequestParam int behaviorId, @RequestBody BehaviorLogEntity newBehaviorLogDetails) {
        return bserv.updateBehaviorLog(behaviorId, newBehaviorLogDetails);
    }

    @DeleteMapping("/deleteBehaviorLog/{behaviorId}")
    public String deleteBehaviorLogLegacy(@PathVariable int behaviorId) {
        return bserv.deleteBehaviorLog(behaviorId);
    }

    // Helper methods
    private Integer extractInteger(Map<String, Object> payload, String... keys) {
        for (String key : keys) {
            Object value = payload.get(key);
            if (value != null) {
                if (value instanceof Integer) {
                    return (Integer) value;
                } else if (value instanceof String) {
                    try {
                        return Integer.parseInt((String) value);
                    } catch (NumberFormatException e) {
                        // Continue to next key
                    }
                }
            }
        }
        return null;
    }

    private String extractString(Map<String, Object> payload, String... keys) {
        for (String key : keys) {
            Object value = payload.get(key);
            if (value != null) {
                return value.toString();
            }
        }
        return null;
    }

    private Date parseDate(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        
        try {
            // Try ISO format first (yyyy-MM-dd)
            return Date.valueOf(s);
        } catch (IllegalArgumentException e) {
            try {
                // Try MM/dd/yyyy format
                java.time.LocalDate ld = java.time.LocalDate.parse(s, 
                    java.time.format.DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                return Date.valueOf(ld);
            } catch (Exception ex) {
                throw new IllegalArgumentException("Invalid date format: " + s + 
                    ". Expected yyyy-MM-dd or MM/dd/yyyy");
            }
        }
    }
}