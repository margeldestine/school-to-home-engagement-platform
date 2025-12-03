package com.appdevg5.geeks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.appdevg5.geeks.entity.CommunicationEntity;
import com.appdevg5.geeks.service.CommunicationService;
import java.sql.Timestamp;
import java.util.Map;

@RestController
@RequestMapping("/api/communication")
@CrossOrigin(origins = "http://localhost:3000")
public class CommunicationController {

    @Autowired
    CommunicationService cserv;

    @PostMapping("/insertCommunicationRecord")
    public CommunicationEntity insertCommunication(@RequestBody CommunicationEntity communication) {
        return cserv.insertCommunication(communication);
    }

    @PostMapping
    public CommunicationEntity createCommunication(@RequestBody Map<String, Object> payload) {
        int teacherId = payload.get("teacher_id") instanceof Number ? ((Number) payload.get("teacher_id")).intValue() : 0;
        String title = (String) payload.getOrDefault("title", "");
        String description = (String) payload.getOrDefault("description", "");
        String details = (String) payload.getOrDefault("details", "");
        String eventDate = (String) payload.getOrDefault("event_date", "");

        CommunicationEntity comm = new CommunicationEntity();
        if (teacherId > 0) {
            comm.setTeacher_id(teacherId);
        }
        comm.setTitle(title);
        comm.setDescription(description);
        comm.setDetails(details);
        comm.setContent(description);
        comm.setPosted_At(new Timestamp(System.currentTimeMillis()));
        if (eventDate != null && !eventDate.isEmpty()) {
            if (!eventDate.contains(" ")) {
                eventDate = eventDate + " 00:00:00";
            }
            comm.setEvent_Date(Timestamp.valueOf(eventDate).toLocalDateTime());
        }

        return cserv.insertCommunication(comm);
    }

    @GetMapping("/getAllCommunications")
    public List<CommunicationEntity> getAllCommunications() {
        return cserv.getAllCommunications();
    }

    @GetMapping
    public List<CommunicationEntity> getCommunications() {
        return cserv.getAllCommunications();
    }

    @PutMapping("/updateCommunication")
    public CommunicationEntity updateCommunication(@RequestParam int communicationId, @RequestBody CommunicationEntity newCommunicationDetails) {
        return cserv.updateCommunication(communicationId, newCommunicationDetails);
    }

    @DeleteMapping("/deleteCommunication/{communicationId}")
    public String deleteCommunication(@PathVariable int communicationId) {
        return cserv.deleteCommunication(communicationId);
    }

    @PostMapping("/class-announcements")
    public CommunicationEntity createClassAnnouncement(@RequestBody Map<String, Object> payload) {
        int teacherId = (Integer) payload.getOrDefault("teacher_id", 0);
        String title = (String) payload.getOrDefault("title", "");
        String description = (String) payload.getOrDefault("description", "");
        String details = (String) payload.getOrDefault("details", "");
        String eventDate = (String) payload.getOrDefault("event_date", "");

        CommunicationEntity comm = new CommunicationEntity();
        comm.setTeacher_id(teacherId);
        comm.setTitle(title);
        comm.setDescription(description);
        comm.setDetails(details);
        comm.setContent(description);
        comm.setPosted_At(new Timestamp(System.currentTimeMillis()));
        if (eventDate != null && !eventDate.isEmpty()) {
            if (!eventDate.contains(" ")) {
                eventDate = eventDate + " 00:00:00";
            }
            comm.setEvent_Date(Timestamp.valueOf(eventDate).toLocalDateTime());
        }

        return cserv.insertCommunication(comm);
    }
}
