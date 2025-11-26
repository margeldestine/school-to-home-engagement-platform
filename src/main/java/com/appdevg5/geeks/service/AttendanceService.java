package com.appdevg5.geeks.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdevg5.geeks.entity.AttendanceEntity;
import com.appdevg5.geeks.repository.AttendanceRepository;

@Service
public class AttendanceService {

    @Autowired
    AttendanceRepository arepo;

    public AttendanceEntity insertAttendanceRecord(AttendanceEntity attendance){
        return arepo.save(attendance);
    }

    public List<AttendanceEntity> getAllAttendances(){
        return arepo.findAll();
    }

    public AttendanceEntity updateAttendance(int aid, AttendanceEntity newAttendanceDetails){
        AttendanceEntity attendance = arepo.findById(aid).orElseThrow(
            () -> new NoSuchElementException("Attendance " + aid + " does not exist!")
        );

        attendance.setStudent_id(newAttendanceDetails.getStudent_id());
        attendance.setTeacher_id(newAttendanceDetails.getTeacher_id());
        attendance.setAttendance_date(newAttendanceDetails.getAttendance_date());
        attendance.setStatus(newAttendanceDetails.getStatus());

        return arepo.save(attendance);
    }

    public String deleteAttendance(int aid){
        String msg = "";

        if(arepo.findById(aid).orElse(null) != null){
            arepo.deleteById(aid);
            msg = "Attendance " + aid + " successfully deleted!";
        }else{
            msg = "Attendance " + aid + " does not exist!";
        }

        return msg;
    }

}

