package com.appdevg5.geeks.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {
    private int teacherId;
    private String firstName;
    private String lastName;
    private String subjectSpecialization;
}

