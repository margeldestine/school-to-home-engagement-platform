package com.appdevg5.geeks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.appdevg5.geeks.entity.CommunicationEntity;
import com.appdevg5.geeks.service.CommunicationService;

@RestController
@RequestMapping("/api/communication")
public class CommunicationController {

    @Autowired
    CommunicationService cserv;

    @PostMapping("/insertCommunicationRecord")
    public CommunicationEntity insertCommunication(@RequestBody CommunicationEntity communication) {
        return cserv.insertCommunication(communication);
    }

    @GetMapping("/getAllCommunications")
    public List<CommunicationEntity> getAllCommunications() {
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
}