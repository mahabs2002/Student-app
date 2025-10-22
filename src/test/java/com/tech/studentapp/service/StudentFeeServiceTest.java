package com.tech.studentapp.service;

import com.tech.studentapp.entity.Student;
import com.tech.studentapp.entity.StudentFee;
import com.tech.studentapp.model.StudentFeeRequest;
import com.tech.studentapp.repository.StudentFeeRepository;
import com.tech.studentapp.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
 public class StudentFeeServiceTest {
    @InjectMocks
    StudentFeeServiceImpl studentFeeService;
    @Mock
    StudentFeeRepository studentFeeRepository;
    @Mock
    StudentRepository studentRepository;
    @Test
    void createFeeTest(){

        Student student=new Student();
        student.setId(1L);

        StudentFeeRequest studentFeeRequest=new StudentFeeRequest();
        studentFeeRequest.setStudentId(1L);
        studentFeeRequest.setRegNo("s1000");
        studentFeeRequest.setFees(20000.0);
        studentFeeRequest.setPaymentStatus("paid");

        List<StudentFeeRequest> feeRequestList = List.of(studentFeeRequest);

        StudentFee studentFee=new StudentFee();
        studentFee.setId(1L);
        studentFee.setRegNo("s1000");
        studentFee.setFees(20000.0);
        studentFee.setPaymentStatus("paid");

        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        Mockito.when(studentFeeRepository.save(Mockito.any(StudentFee.class))).thenReturn(studentFee);

        //Mockito.when(studentFeeRepository.save(studentFee)).thenReturn(studentFee);
        List<StudentFee> addedstudent=studentFeeService.createFee(feeRequestList);

        Assertions.assertEquals(1L, addedstudent.get(0).getId());
        Assertions.assertEquals("s1000", addedstudent.get(0).getRegNo());

    }


}