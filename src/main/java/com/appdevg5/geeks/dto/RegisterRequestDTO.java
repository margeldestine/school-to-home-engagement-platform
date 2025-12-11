package com.appdevg5.geeks.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDTO {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String role;
    private String contactNumber;
    private String studentNumber;
    private String teacherIdNumber;

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getTeacherIdNumber() {
        return teacherIdNumber;
    }

    public void setTeacherIdNumber(String teacherIdNumber) {
        this.teacherIdNumber = teacherIdNumber;
    }
}

