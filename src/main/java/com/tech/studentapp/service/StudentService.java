package com.tech.studentapp.service;

import com.tech.studentapp.entity.Student;
import com.tech.studentapp.entity.StudentFee;
import com.tech.studentapp.model.StudResponse;
import com.tech.studentapp.model.StudentResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudents();

    List<StudentResponse> getStudentByDepartment(String department);

    List<StudResponse> getStudentPagination(int page, int size);

    StudentResponse getStudentById(Long id);


    List<Student>  saveStudent(List<Student> student);

    Student updateStudent(Long id,Student updatestudent);

   void deleteStudent(Long id);



}
