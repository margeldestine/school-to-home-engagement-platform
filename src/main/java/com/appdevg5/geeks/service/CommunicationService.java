package com.appdevg5.geeks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import com.appdevg5.geeks.entity.CommunicationEntity;
import com.appdevg5.geeks.repository.CommunicationRepository;

@Service
public class CommunicationService {

    @Autowired
    CommunicationRepository crepo;

    public CommunicationEntity insertCommunication(CommunicationEntity communication) {
        return crepo.save(communication);
    }

    public List<CommunicationEntity> getAllCommunications() {
        return crepo.findAll();
    }

    public CommunicationEntity updateCommunication(int communicationId, CommunicationEntity newCommunicationDetails) {

        CommunicationEntity communication = crepo.findById(communicationId)
                .orElseThrow(() -> new NoSuchElementException("Communication " + communicationId + " does not exist"));

        communication.setTeacher_id(newCommunicationDetails.getTeacher_id());
        communication.setAdmin_id(newCommunicationDetails.getAdmin_id());
        communication.setTitle(newCommunicationDetails.getTitle());
        communication.setContent(newCommunicationDetails.getContent());
        communication.setEvent_Date(newCommunicationDetails.getEvent_Date());
        communication.setPosted_At(newCommunicationDetails.getPosted_At());

        return crepo.save(communication);
    }

    public String deleteCommunication(int communicationId) {
        if (crepo.existsById(communicationId)) {
            crepo.deleteById(communicationId);
            return "Communication " + communicationId + " is successfully deleted!";
        } else {
            return "Communication " + communicationId + " does not exist.";
        }
    }
}
