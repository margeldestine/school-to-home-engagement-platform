package com.appdevg5.geeks.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormSignatureDTO {
    private int signatureId;
    private int formId;
    private String formTitle;
    private int parentId;
    private String parentName;
    private int studentId;
    private String studentName;
    private LocalDateTime signedAt;
}

