package com.tech.studentapp.repository;

import com.tech.studentapp.entity.StudentFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentFeeRepository  extends JpaRepository<StudentFee,Long> {
}
