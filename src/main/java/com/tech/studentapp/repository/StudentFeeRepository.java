package com.tech.studentapp.repository;

import com.tech.studentapp.entity.StudentFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentFeeRepository  extends JpaRepository<StudentFee,Long> {

    List<StudentFee> findByPaymentStatus(String paymentStatus);
}
