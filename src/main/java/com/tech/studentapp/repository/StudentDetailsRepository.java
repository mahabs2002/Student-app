package com.tech.studentapp.repository;

import com.tech.studentapp.entity.StudentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDetailsRepository extends JpaRepository<StudentDetails,Long> {
}
