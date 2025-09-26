package com.tech.studentapp.controller;

import com.tech.studentapp.entity.Student;
import com.tech.studentapp.model.StudentRequest;
import com.tech.studentapp.model.StudentResponse;
import com.tech.studentapp.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/stud")
@RestController
public class StudentController {

    private  final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents(){
        return  studentService.getAllStudents();
    }

    @GetMapping("/getStudent")
    public StudentResponse getStudentById(@RequestBody StudentRequest studentRequest){
        return studentService.getStudentById(studentRequest.getId());

    }

    @PostMapping("/savestud")
    public ResponseEntity<Void> createStudent(@RequestBody List<Student> student){
        studentService.saveStudent(student);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateStudent(@RequestBody Student student){
    Student updatedStudent=studentService.updateStudent(student.getId(),student);
        if(updatedStudent !=null){
            return ResponseEntity.ok().build();
        }else{
            return  ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> deleteStudent(@RequestBody Student student) {
        try {
            studentService.deleteStudent(student.getId());
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

}
