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

        if (newCommunicationDetails.getTeacher_id() > 0) {
            communication.setTeacher_id(newCommunicationDetails.getTeacher_id());
        }
        if (newCommunicationDetails.getAdmin_id() > 0) {
            communication.setAdmin_id(newCommunicationDetails.getAdmin_id());
        }
        if (newCommunicationDetails.getTitle() != null) {
            communication.setTitle(newCommunicationDetails.getTitle());
        }
        if (newCommunicationDetails.getContent() != null) {
            communication.setContent(newCommunicationDetails.getContent());
        }
        if (newCommunicationDetails.getDescription() != null) {
            communication.setDescription(newCommunicationDetails.getDescription());
        }
        if (newCommunicationDetails.getDetails() != null) {
            communication.setDetails(newCommunicationDetails.getDetails());
        }
        if (newCommunicationDetails.getEvent_Date() != null) {
            communication.setEvent_Date(newCommunicationDetails.getEvent_Date());
        }
        if (newCommunicationDetails.getPosted_At() != null) {
            communication.setPosted_At(newCommunicationDetails.getPosted_At());
        }

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
