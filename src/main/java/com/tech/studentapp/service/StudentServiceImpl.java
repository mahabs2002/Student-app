package com.tech.studentapp.service;

import com.tech.studentapp.entity.Student;
import com.tech.studentapp.entity.StudentDetails;
import com.tech.studentapp.entity.StudentFee;
import com.tech.studentapp.exception.StudentNotFoundException;
import com.tech.studentapp.model.ErrorResponse;
import com.tech.studentapp.model.StudResponse;
import com.tech.studentapp.model.StudentDetailResponse;
import com.tech.studentapp.model.StudentResponse;
import com.tech.studentapp.repository.StudentDetailsRepository;
import com.tech.studentapp.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
@Service
public class StudentServiceImpl implements  StudentService{

    private  final StudentRepository studentRepo;
    private final StudentDetailsRepository studentDetailsRepository;
    private final StudentPaginationRepository studentPaginationRepository;

    public StudentServiceImpl(StudentRepository studentRepo, StudentDetailsRepository studentDetailsRepository, StudentPaginationRepository studentPaginationRepository) {
        this.studentRepo = studentRepo;
        this.studentDetailsRepository = studentDetailsRepository;
        this.studentPaginationRepository = studentPaginationRepository;
    }


    @Override
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    public List<StudentResponse> getStudentByDepartment(String department) {
        List<StudentDetails> students=studentDetailsRepository.findByDepartment(department);

        return students.stream()
                .map(s->{
                    StudentDetailResponse detailResponse=new StudentDetailResponse();

                       detailResponse=new StudentDetailResponse();
                       detailResponse.setId(s.getId());
                        detailResponse.setRegNo(s.getRegNo());
                        detailResponse.setCity(s.getCity());
                        detailResponse.setCgpa(s.getCgpa());
                        detailResponse.setDepartment(s.getDepartment());
                    Student student = s.getStudent();
                    StudentResponse response = new StudentResponse();
                    response.setId(student.getId());
                    response.setStudentName(student.getStudentName());
                    response.setDoj(student.getDoj());
                    response.setDoe(student.getDoe());
                    response.setLpa(student.getLpa());
                    response.setPlacedCompany(student.getPlacedCompany());
                    response.setStudentDetailResponse(detailResponse);

                    return response;
                }).toList();


    }

    @Override
    public List<StudResponse> getStudentPagination(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Student> students=studentPaginationRepository.findAll(pageable);
        return students.getContent()
                .stream()
                .map(s->StudResponse.builder()
                        .id(s.getId())
                        .studentName(s.getStudentName())
                        .doj(s.getDoj())
                        .doe(s.getDoe())
                        .lpa(s.getLpa())
                        .placedCompany(s.getPlacedCompany())
                        .build()
                ).toList();
    }


    @Override
    public StudentResponse getStudentById(Long id) {
       // return studentRepo.findById(id).orElse(null);
    Student student = studentRepo.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found " + id));
    StudentResponse response = new StudentResponse();
    response.setId(student.getId());
    response.setStudentName(student.getStudentName());
    response.setDoj(student.getDoj());
    response.setDoe(student.getDoe());
    response.setLpa(student.getLpa());
    response.setPlacedCompany(student.getPlacedCompany());

    if (student.getStudentDetails() != null) {
        StudentDetailResponse res = new StudentDetailResponse();
        res.setId(student.getStudentDetails().getId());
        res.setRegNo(student.getStudentDetails().getRegNo());
        res.setRegNo(student.getStudentDetails().getRegNo());
        res.setCity(student.getStudentDetails().getCity());
        res.setCgpa(student.getStudentDetails().getCgpa());
        res.setDepartment(student.getStudentDetails().getDepartment());

        response.setStudentDetailResponse(res);
    }
        return response;

    }





    @Override
    public List<Student> saveStudent(List<Student> student) {
        student.forEach(stu->{
            if(stu.getStudentDetails()!=null){
            stu.getStudentDetails().setStudent(stu);
            }
        });
        System.out.println("Successfully Added");
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
