package com.tech.studentapp.repository;

import com.tech.studentapp.entity.Student;
import com.tech.studentapp.entity.StudentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentDetailsRepository extends JpaRepository<StudentDetails,Long> {
    List<StudentDetails> findByDepartment(String department);
}
