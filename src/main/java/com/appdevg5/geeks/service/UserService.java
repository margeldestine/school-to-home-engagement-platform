package com.appdevg5.geeks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import com.appdevg5.geeks.entity.UserEntity;
import com.appdevg5.geeks.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository urepo;

    public UserEntity insertUserRecord(UserEntity user) {
        return urepo.save(user);
    }

    public List<UserEntity> getAllUsers() {
        return urepo.findAll();
    }

    @SuppressWarnings("finally")
    public UserEntity updateUser(int uid, UserEntity newUserDetails) {
        UserEntity user = new UserEntity();
        try {
            user = urepo.findById(uid).get();
            user.setEmail(newUserDetails.getEmail());
            user.setPassword(newUserDetails.getPassword());
            user.setFirst_name(newUserDetails.getFirst_name());
            user.setLast_name(newUserDetails.getLast_name());
            user.setRole(newUserDetails.getRole());
            user.setCreated_at(newUserDetails.getCreated_at());
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
