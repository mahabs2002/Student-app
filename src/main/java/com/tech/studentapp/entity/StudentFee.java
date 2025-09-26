package com.tech.studentapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentFee {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String regNo;
private double fees;
private String paymentStatus;
@OneToOne
@JoinColumn(name="student_id",referencedColumnName = "id")
private Student student;
}
