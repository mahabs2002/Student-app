package com.tech.studentapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudResponse {
    private Long id;
    private String studentName;
    private String doj;
    private String doe;
    private double lpa;
    private String placedCompany;
}
