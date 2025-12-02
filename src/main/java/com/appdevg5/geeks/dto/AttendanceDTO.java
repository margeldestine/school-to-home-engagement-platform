package com.appdevg5.geeks.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceDTO {
    private int attendanceId;
    private int studentId;
    private String studentName;
    private LocalDate date;
    private String status;
    private String subjectName;
}

