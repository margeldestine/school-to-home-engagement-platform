package com.appdevg5.geeks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import com.appdevg5.geeks.entity.UserEntity;
import com.appdevg5.geeks.entity.TeacherEntity;
import com.appdevg5.geeks.entity.ParentEntity;
import com.appdevg5.geeks.repository.UserRepository;
import com.appdevg5.geeks.repository.TeacherRepository;
import com.appdevg5.geeks.repository.ParentRepository;

@Service
public class UserService {

    @Autowired
    UserRepository urepo;

    @Autowired
    TeacherRepository trepo;

    @Autowired
    ParentRepository prepo;

    public UserEntity insertUserRecord(UserEntity user) {
        // Save the user first
        UserEntity savedUser = urepo.save(user);
        
        // Create corresponding Teacher or Parent entity based on role
        if ("TEACHER".equalsIgnoreCase(savedUser.getRole())) {
            TeacherEntity teacher = new TeacherEntity();
            teacher.setUser(savedUser);
            trepo.save(teacher);
        } else if ("PARENT".equalsIgnoreCase(savedUser.getRole())) {
            ParentEntity parent = new ParentEntity();
            parent.setUser(savedUser);
            prepo.save(parent);
        }
        
        return savedUser;
    }

    public List<UserEntity> getAllUsers() {
        return urepo.findAll();
    }

    @SuppressWarnings("finally")
    public UserEntity updateUser(int uid, UserEntity newUserDetails) {
        UserEntity user = new UserEntity();
        try {
            user = urepo.findById(uid).get();
            String oldRole = user.getRole();
            
            user.setEmail(newUserDetails.getEmail());
            user.setPassword(newUserDetails.getPassword());
            user.setFirst_name(newUserDetails.getFirst_name());
            user.setLast_name(newUserDetails.getLast_name());
            user.setRole(newUserDetails.getRole());
            user.setCreated_at(newUserDetails.getCreated_at());
            
            // If role changed, create appropriate entity
            if (!oldRole.equalsIgnoreCase(newUserDetails.getRole())) {
                if ("TEACHER".equalsIgnoreCase(newUserDetails.getRole())) {
                    TeacherEntity teacher = new TeacherEntity();
                    teacher.setUser(user);
                    trepo.save(teacher);
                } else if ("PARENT".equalsIgnoreCase(newUserDetails.getRole())) {
                    ParentEntity parent = new ParentEntity();
                    parent.setUser(user);
                    prepo.save(parent);
                }
            }
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("User " + uid + " does not exist.");
        } finally {
            if (user.getUser_id() == 0) {
                throw new NoSuchElementException("User " + uid + " does not exist.");
            }
            return urepo.save(user);
        }
    }

    public String deleteUser(int uid) {
        if (urepo.existsById(uid)) {
            urepo.deleteById(uid);
            return "User " + uid + " is successfully deleted!";
        } else {
            return "User " + uid + " does not exist.";
        }
    }
}