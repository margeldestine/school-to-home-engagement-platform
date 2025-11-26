package com.appdevg5.geeks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import com.appdevg5.geeks.entity.StudentEntity;
import com.appdevg5.geeks.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    StudentRepository srepo;

    public StudentEntity insertStudentRecord(StudentEntity student) {
        return srepo.save(student);
    }

    public List<StudentEntity> getAllStudents() {
        return srepo.findAll();
    }

    public StudentEntity updateStudent(int studentId, StudentEntity newStudentDetails) {

    StudentEntity student = srepo.findById(studentId)
        .orElseThrow(() -> new NoSuchElementException("Student " + studentId + " does not exist"));

    student.setStudent_number(newStudentDetails.getStudent_number());
    student.setFirst_name(newStudentDetails.getFirst_name());
    student.setLast_name(newStudentDetails.getLast_name());
    student.setGrade_level(newStudentDetails.getGrade_level());
    student.setSection(newStudentDetails.getSection());

        return srepo.save(student);
    }

    public String deleteStudent(int studentId) {
        if (srepo.existsById(studentId)) {
            srepo.deleteById(studentId);
            return "Student " + studentId + " is successfully deleted!";
        } else {
            return "Student " + studentId + " does not exist.";
        }
    }
}
 