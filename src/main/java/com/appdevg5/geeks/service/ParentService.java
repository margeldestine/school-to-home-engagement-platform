package com.appdevg5.geeks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import com.appdevg5.geeks.entity.ParentEntity;
import com.appdevg5.geeks.repository.ParentRepository;
import com.appdevg5.geeks.repository.UserRepository;
import com.appdevg5.geeks.entity.UserEntity;

@Service
public class ParentService {

    @Autowired
    ParentRepository prepo;

    public ParentEntity insertParentRecord(ParentEntity parent) {
        return prepo.save(parent);
    }

    public List<ParentEntity> getAllParents() {
        return prepo.findAll();
    }

    public ParentEntity updateParent(int parentId, ParentEntity newParentDetails) {
    ParentEntity parent = prepo.findById(parentId)
        .orElseThrow(() -> new NoSuchElementException("Parent " + parentId + " does not exist"));

        return prepo.save(parent);
    }

    public String deleteParent(int parentId) {
        if (prepo.existsById(parentId)) {
            prepo.deleteById(parentId);
            return "Parent " + parentId + " is successfully deleted!";
        } else {
            return "Parent " + parentId + " does not exist.";
        }
    }

    public ParentEntity getParentByUserId(int userId) {
        return prepo.findByUserId(userId)
            .orElseThrow(() -> new NoSuchElementException("Parent not found for user_id: " + userId));
    }
}
 