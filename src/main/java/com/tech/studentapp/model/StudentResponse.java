package com.tech.studentapp.model;

import lombok.Data;

@Data
public class StudentResponse {
    private Long id;
    private String studentName;
    private String doj;
    private String doe;
    private double lpa;
    private String placedCompany;
    private StudentDetailResponse studentDetailResponse;
}
