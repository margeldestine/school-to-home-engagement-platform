package com.appdevg5.geeks.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdevg5.geeks.entity.AttendanceEntity;
import com.appdevg5.geeks.entity.StudentEntity;
import com.appdevg5.geeks.entity.TeacherEntity;
import com.appdevg5.geeks.repository.AttendanceRepository;
import com.appdevg5.geeks.repository.StudentRepository;
import com.appdevg5.geeks.repository.TeacherRepository;

@Service
public class AttendanceService {

    @Autowired
    AttendanceRepository arepo;
    
    @Autowired
    StudentRepository srepo;
    
    @Autowired
    TeacherRepository trepo;

    public AttendanceEntity createAttendanceFromIds(int studentId, int teacherId, Timestamp date, String status) {
        StudentEntity student = srepo.findById(studentId)
            .orElseThrow(() -> new NoSuchElementException("Student with ID " + studentId + " not found"));
        
        TeacherEntity teacher = trepo.findById(teacherId)
            .orElseThrow(() -> new NoSuchElementException("Teacher with ID " + teacherId + " not found"));
        
        AttendanceEntity attendance = new AttendanceEntity();
        attendance.setStudent(student);
        attendance.setTeacher(teacher);
        attendance.setAttendance_date(date);
        attendance.setStatus(status);
        
        return arepo.save(attendance);
    }

    public AttendanceEntity insertAttendanceRecord(AttendanceEntity attendance){
        if (attendance.getStudent() != null && attendance.getStudent().getStudent_id() > 0) {
            StudentEntity student = srepo.findById(attendance.getStudent().getStudent_id())
                .orElseThrow(() -> new NoSuchElementException("Student not found"));
            attendance.setStudent(student);
        }
        
        if (attendance.getTeacher() != null && attendance.getTeacher().getTeacher_id() > 0) {
            TeacherEntity teacher = trepo.findById(attendance.getTeacher().getTeacher_id())
                .orElseThrow(() -> new NoSuchElementException("Teacher not found"));
            attendance.setTeacher(teacher);
        }
        
        return arepo.save(attendance);
    }

    public List<AttendanceEntity> insertBatchAttendance(List<AttendanceEntity> attendances){
        for (AttendanceEntity attendance : attendances) {
            if (attendance.getStudent() != null && attendance.getStudent().getStudent_id() > 0) {
                StudentEntity student = srepo.findById(attendance.getStudent().getStudent_id())
                    .orElseThrow(() -> new NoSuchElementException("Student not found"));
                attendance.setStudent(student);
            }
            
            if (attendance.getTeacher() != null && attendance.getTeacher().getTeacher_id() > 0) {
                TeacherEntity teacher = trepo.findById(attendance.getTeacher().getTeacher_id())
                    .orElseThrow(() -> new NoSuchElementException("Teacher not found"));
                attendance.setTeacher(teacher);
            }
        }
        return arepo.saveAll(attendances);
    }

    public List<AttendanceEntity> getAllAttendances(){
        return arepo.findAll();
    }

    public AttendanceEntity updateAttendance(int aid, AttendanceEntity newAttendanceDetails){
        AttendanceEntity attendance = arepo.findById(aid).orElseThrow(
            () -> new NoSuchElementException("Attendance " + aid + " does not exist!")
        );

        if (newAttendanceDetails.getStudent_id() > 0) {
            StudentEntity student = srepo.findById(newAttendanceDetails.getStudent_id())
                .orElseThrow(() -> new NoSuchElementException("Student not found"));
            attendance.setStudent(student);
        }
        
        if (newAttendanceDetails.getTeacher_id() > 0) {
            TeacherEntity teacher = trepo.findById(newAttendanceDetails.getTeacher_id())
                .orElseThrow(() -> new NoSuchElementException("Teacher not found"));
            attendance.setTeacher(teacher);
        }
        
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