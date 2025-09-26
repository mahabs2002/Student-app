package com.tech.studentapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentFeeResponse {

    private Long id;
    private String regNo;
    private double fees;
    private String paymentStatus;
    private StudentResponse student;
}
