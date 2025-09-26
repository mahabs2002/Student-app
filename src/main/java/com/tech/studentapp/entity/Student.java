package com.tech.studentapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String studentName;
    private String doj;
    private String doe;
    private double lpa;
    private String placedCompany;

    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL,orphanRemoval = true)
    private StudentDetails studentDetails;

}
