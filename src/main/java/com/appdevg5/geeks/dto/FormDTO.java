package com.appdevg5.geeks.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormDTO {
    private int formId;
    private String title;
    private String details;
    private LocalDateTime dueDate;
    private LocalDateTime createdAt;
    private String teacherName;
}

