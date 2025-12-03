package com.appdevg5.geeks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import com.appdevg5.geeks.entity.TeacherEntity;
import com.appdevg5.geeks.repository.TeacherRepository;


@Service
public class TeacherService {

    @Autowired
    TeacherRepository trepo;

    public TeacherEntity insertTeacherRecord(TeacherEntity teacher) {
        return trepo.save(teacher);
    }

    public List<TeacherEntity> getAllTeachers() {
        return trepo.findAll();
    }

    public TeacherEntity updateTeacher(int tid, TeacherEntity newTeacherDetails) {
        TeacherEntity teacher = trepo.findById(tid).orElseThrow(
            () -> new NoSuchElementException("Teacher " + tid + " does not exist!")
        );
        teacher.setUser(newTeacherDetails.getUser());
        return trepo.save(teacher);
    }

    public String deleteTeacher(int tid) {
        if (trepo.existsById(tid)) {
            trepo.deleteById(tid);
            return "Teacher " + tid + " is successfully deleted!";
        } else {
            return "Teacher " + tid + " does not exist.";
        }
    }

    public TeacherEntity getTeacherByUserId(int userId) {
        return trepo.findByUserId(userId)
            .orElseThrow(() -> new NoSuchElementException("Teacher not found for user_id: " + userId));
    }

    
    
}