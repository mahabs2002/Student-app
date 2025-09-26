package com.tech.studentapp.model;

import lombok.Data;

@Data
public class StudentDetailResponse {
    private Long id;
    private String regNo;
    private String city;
    private Double cgpa;
    private  String department;

}
