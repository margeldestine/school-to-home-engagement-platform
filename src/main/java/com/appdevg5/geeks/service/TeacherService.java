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

    @SuppressWarnings("finally")
    public TeacherEntity updateTeacher(int tid, TeacherEntity newTeacherDetails) {
        TeacherEntity teacher = new TeacherEntity();
        try {
            teacher = trepo.findById(tid).get();
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Teacher " + tid + " does not exist.");
        } finally {
            if (teacher.getTeacher_id() == 0) {
                throw new NoSuchElementException("Teacher " + tid + " does not exist.");
            }
            return trepo.save(teacher);
        }
    }

    public String deleteTeacher(int tid) {
        if (trepo.existsById(tid)) {
            trepo.deleteById(tid);
            return "Teacher " + tid + " is successfully deleted!";
        } else {
            return "Teacher " + tid + " does not exist.";
        }
    }
}
