package com.appdevg5.geeks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import com.appdevg5.geeks.entity.SubjectEntity;
import com.appdevg5.geeks.repository.SubjectRepository;

@Service
public class SubjectService {

    @Autowired
    SubjectRepository srepo;

    public SubjectEntity insertSubjectRecord(SubjectEntity subject) {
        return srepo.save(subject);
    }

    public List<SubjectEntity> getAllSubjects() {
        return srepo.findAll();
    }

    public SubjectEntity updateSubject(int subjectId, SubjectEntity newSubjectDetails) {
        SubjectEntity subject = srepo.findById(subjectId)
            .orElseThrow(() -> new NoSuchElementException("Subject " + subjectId + " does not exist"));

        subject.setSubject_code(newSubjectDetails.getSubject_code());
        subject.setSubject_name(newSubjectDetails.getSubject_name());

        return srepo.save(subject);
    }

    public String deleteSubject(int subjectId) {
        if (srepo.existsById(subjectId)) {
            srepo.deleteById(subjectId);
            return "Subject " + subjectId + " is successfully deleted!";
        } else {
            return "Subject " + subjectId + " does not exist.";
        }
    }
}

