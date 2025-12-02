package com.appdevg5.geeks.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParentDTO {
    private int parentId;
    private String firstName;
    private String lastName;
    private String email;
    private Integer userId;
}

