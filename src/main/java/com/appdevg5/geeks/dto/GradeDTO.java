package com.appdevg5.geeks.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeDTO {
    private int gradeId;
    private int studentId;
    private String studentName;
    private String subjectName;
    private float gradeValue;
    private int gradingPeriod;
}

