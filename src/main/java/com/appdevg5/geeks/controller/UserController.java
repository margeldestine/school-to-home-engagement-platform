package com.appdevg5.geeks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.appdevg5.geeks.entity.UserEntity;
import com.appdevg5.geeks.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userv;

    @PostMapping("/insertUserRecord")
    public UserEntity insertUserRecord(@RequestBody UserEntity user) {
        return userv.insertUserRecord(user);
    }

    @GetMapping("/getAllUsers")
    public List<UserEntity> getAllUsers() {
        return userv.getAllUsers();
    }

    @PutMapping("/updateUser")
    public UserEntity updateUser(@RequestParam int uid, @RequestBody UserEntity newUserDetails) {
        return userv.updateUser(uid, newUserDetails);
    }

    @DeleteMapping("/deleteUser/{uid}")
    public String deleteUser(@PathVariable int uid) {
        return userv.deleteUser(uid);
    }
}
