package com.appdevg5.geeks.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommunicationDTO {
    private int communicationId;
    private String title;
    private String content;
    private LocalDateTime eventDate;
    private LocalDateTime postedAt;
    private String teacherName;
}

