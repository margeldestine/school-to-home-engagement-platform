package com.appdevg5.geeks.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BehaviorLogDTO {
    private int behaviorId;
    private int studentId;
    private String studentName;
    private String type;
    private String description;
    private LocalDate incidentDate;
    private LocalDateTime recordedAt;
}

