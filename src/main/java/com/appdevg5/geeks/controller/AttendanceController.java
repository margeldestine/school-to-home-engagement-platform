package com.appdevg5.geeks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appdevg5.geeks.entity.AttendanceEntity;
import com.appdevg5.geeks.service.AttendanceService;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    AttendanceService aserv;

    @PostMapping("/insertAttendanceRecord")
    public AttendanceEntity insertAttendanceRecord(@RequestBody AttendanceEntity attendance){
        return aserv.insertAttendanceRecord(attendance);
    }

    @GetMapping("/getAllAttendances")
    public List<AttendanceEntity> getAllAttendances(){
        return aserv.getAllAttendances();
    }

    @PutMapping("/updateAttendance")
    public AttendanceEntity updateAttendance(@RequestParam int aid, @RequestBody AttendanceEntity attendance){
        return aserv.updateAttendance(aid, attendance);
    }

    @DeleteMapping("/deleteAttendance/{aid}")
    public String deleteAttendance(@PathVariable int aid){
        return aserv.deleteAttendance(aid);
    }
}
