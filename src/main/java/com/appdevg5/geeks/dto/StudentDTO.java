package com.appdevg5.geeks.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private int studentId;
    private int studentNumber;
    private String firstName;
    private String lastName;
    private int gradeLevel;
    private String sectionName;
}

