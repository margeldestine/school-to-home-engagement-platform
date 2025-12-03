package com.appdevg5.geeks.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.CrossOrigin;

import com.appdevg5.geeks.entity.AttendanceEntity;
import com.appdevg5.geeks.service.AttendanceService;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "http://localhost:3000")
public class AttendanceController {

    @Autowired
    AttendanceService aserv;

    @PostMapping("/insertAttendanceRecord")
    public AttendanceEntity insertAttendanceRecord(@RequestBody AttendanceEntity attendance){
        return aserv.insertAttendanceRecord(attendance);
    }

    @PostMapping
    public AttendanceEntity createAttendance(@RequestBody Map<String, Object> payload){
        int studentId = (Integer) payload.get("student_id");
        int teacherId = (Integer) payload.get("teacher_id");
        String dateString = (String) payload.get("attendance_date");
        String status = (String) payload.get("status");
        
        Timestamp timestamp = Timestamp.valueOf(dateString);
        
        return aserv.createAttendanceFromIds(studentId, teacherId, timestamp, status);
    }

    @PostMapping("/batch")
    public List<AttendanceEntity> createBatchAttendance(@RequestBody List<AttendanceEntity> attendances){
        return aserv.insertBatchAttendance(attendances);
    }

    @GetMapping("/getAllAttendances")
    public List<AttendanceEntity> getAllAttendances(){
        return aserv.getAllAttendances();
    }

    @GetMapping
    public List<AttendanceEntity> getAttendances(){
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