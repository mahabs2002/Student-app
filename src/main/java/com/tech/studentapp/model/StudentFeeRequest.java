package com.tech.studentapp.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentFeeRequest {

    private Long studentId;
    private  String regNo;
    private double fees;
    private  String paymentStatus;
}
