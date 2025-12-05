package com.tech.studentapp.service;

import com.tech.studentapp.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentPaginationRepository extends JpaRepository<Student,Long> {
}
