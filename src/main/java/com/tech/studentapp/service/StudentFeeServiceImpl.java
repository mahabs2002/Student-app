package com.tech.studentapp.service;

import com.tech.studentapp.entity.Student;
import com.tech.studentapp.entity.StudentFee;
import com.tech.studentapp.model.StudentDetailResponse;
import com.tech.studentapp.model.StudentFeeRequest;
import com.tech.studentapp.model.StudentFeeResponse;
import com.tech.studentapp.model.StudentResponse;
import com.tech.studentapp.repository.StudentFeeRepository;
import com.tech.studentapp.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentFeeServiceImpl implements StudentFeeService {

    private final StudentRepository studentRepo;
    private final StudentFeeRepository studentFeeRepo;

    public StudentFeeServiceImpl(StudentRepository studentRepo, StudentFeeRepository studentFeeRepo) {
        this.studentRepo = studentRepo;
        this.studentFeeRepo = studentFeeRepo;
    }


    @Override
    public List<StudentFee> createFee(List<StudentFeeRequest> feeRequest) {

        List<StudentFee> savedFee=new ArrayList<>();
        for (StudentFeeRequest feeRequests:feeRequest) {
            Student student = studentRepo.findById(feeRequests.getStudentId())
                    .orElseThrow(() -> new RuntimeException("Student not found: ID " + feeRequests.getStudentId()));


            StudentFee fee = new StudentFee();
            fee.setRegNo(feeRequests.getRegNo());
            fee.setFees(feeRequests.getFees());
            fee.setPaymentStatus(feeRequests.getPaymentStatus());
            fee.setStudent(student);
            savedFee.add(studentFeeRepo.save(fee));
        }
        return savedFee;
    }

    @Override
    public StudentFeeResponse getFeeById(Long studentId) {
       StudentFee fee= studentFeeRepo.findById(studentId).orElseThrow(()->new RuntimeException("not found"));
        StudentFeeResponse response=new StudentFeeResponse();
        response.setId(fee.getId());
        response.setRegNo(fee.getRegNo());
        response.setFees(fee.getFees());
        response.setPaymentStatus(fee.getPaymentStatus());

        if(fee.getStudent()!=null){
            Student student=fee.getStudent();
            StudentResponse studentResponse=new StudentResponse();
            studentResponse.setId(student.getId());
            studentResponse.setStudentName(student.getStudentName());
            studentResponse.setDoj(student.getDoj());
            studentResponse.setDoe(student.getDoe());
            studentResponse.setLpa(student.getLpa());
            studentResponse.setPlacedCompany(student.getPlacedCompany());

            if(student.getStudentDetails()!=null){
                StudentDetailResponse detailResponse=new StudentDetailResponse();
                detailResponse.setId(student.getStudentDetails().getId());
                detailResponse.setRegNo(student.getStudentDetails().getRegNo());
                detailResponse.setCity(student.getStudentDetails().getCity());
                detailResponse.setCgpa(student.getStudentDetails().getCgpa());
                detailResponse.setDepartment(student.getStudentDetails().getDepartment());
            }
            response.setStudent(studentResponse);
        }
        return response;


    }

    @Override
    public StudentFee updateFee(Long id, StudentFee studentFee) {
        return studentFeeRepo.findById(id).map(oldfee->{
            oldfee.setRegNo(studentFee.getRegNo());
            oldfee.setFees(studentFee.getFees());
            oldfee.setPaymentStatus(studentFee.getPaymentStatus());
            return  studentFeeRepo.save(oldfee);
                }).
                orElseThrow(()->new RuntimeException("id not found"));

    }

    @Override
    public void deleteFee(Long id) {
        if(studentFeeRepo.existsById(id)){
        studentFeeRepo.deleteById(id);
    }else{
            throw new RuntimeException("id not found");
        }
}

    @Override
    public StudentFee getStudentFeeById(Long id) {
        return studentFeeRepo.findById(id).
                orElseThrow(()->new RuntimeException("id not found"));
    }


}
