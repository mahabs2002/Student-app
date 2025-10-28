package com.tech.studentapp.service;

import com.tech.studentapp.entity.Student;
import com.tech.studentapp.entity.StudentFee;
import com.tech.studentapp.model.StudentDetailResponse;
import com.tech.studentapp.model.StudentResponse;
import com.tech.studentapp.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements  StudentService{

    private  final StudentRepository studentRepo;

    public StudentServiceImpl(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }


    @Override
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    public StudentResponse getStudentById(Long id) {
       // return studentRepo.findById(id).orElse(null);

        Student student= studentRepo.findById(id).orElse(null);
        StudentResponse response=new StudentResponse();
        response.setId(student.getId());
        response.setStudentName(student.getStudentName());
        response.setDoj(student.getDoj());
        response.setDoe(student.getDoe());
        response.setLpa(student.getLpa());
        response.setPlacedCompany(student.getPlacedCompany());

        if(student.getStudentDetails()!=null){
            StudentDetailResponse res=new StudentDetailResponse();
            res.setId(student.getStudentDetails().getId());
            res.setRegNo(student.getStudentDetails().getRegNo());
            res.setRegNo(student.getStudentDetails().getRegNo());
           res.setCity(student.getStudentDetails().getCity());
            res.setCgpa(student.getStudentDetails().getCgpa());
            res.setDepartment(student.getStudentDetails().getDepartment());

            response.setStudentDetailResponse(res);
        }
return  response;
    }

    @Override
    public List<Student> saveStudent(List<Student> student) {
        student.forEach(stu->{
            if(stu.getStudentDetails()!=null){
            stu.getStudentDetails().setStudent(stu);
            }
        });
        return studentRepo.saveAll(student);
    }





    @Override
    public Student updateStudent(Long id, Student updatestudent) {
        return studentRepo.findById(id).map(stu ->{
            stu.setStudentName(updatestudent.getStudentName());
            stu.setDoj(updatestudent.getDoj());
            stu.setDoe(updatestudent.getDoe());
            stu.setLpa(updatestudent.getLpa());
            stu.setPlacedCompany(updatestudent.getPlacedCompany());

            if(updatestudent.getStudentDetails()!=null){
                stu.getStudentDetails().setRegNo(updatestudent.getStudentDetails().getRegNo());
                stu.getStudentDetails().setCity(updatestudent.getStudentDetails().getCity());
                stu.getStudentDetails().setCgpa(updatestudent.getStudentDetails().getCgpa());
                stu.getStudentDetails().setDepartment(updatestudent.getStudentDetails().getDepartment());
            }

            return studentRepo.save(stu);

        }).orElse(null);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepo.deleteById(id);
    }


 private boolean validateStudentName(String name){
     return name!=null && !name.isEmpty();
 }

}
