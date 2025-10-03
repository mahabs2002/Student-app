package com.tech.studentapp.service;

import com.tech.studentapp.entity.StudentFee;
import com.tech.studentapp.model.StudentFeeRequest;
import com.tech.studentapp.model.StudentFeeResponse;

import java.util.List;

public interface StudentFeeService {

    List<StudentFee> createFee(List<StudentFeeRequest> feeRequest);

    StudentFeeResponse getFeeById(Long studentId);

    StudentFee updateFee(Long id,StudentFee studentFee);

    void deleteFee(Long id);
    StudentFee getStudentFeeById(Long id);
}
