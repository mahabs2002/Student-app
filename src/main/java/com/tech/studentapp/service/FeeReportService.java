package com.tech.studentapp.service;

import com.tech.studentapp.entity.StudentFee;

import java.util.List;

public interface FeeReportService {

    byte[] generateFeeReport() throws Exception;
    List<StudentFee> getAllFees();
}
