package com.tech.studentapp.controller;

import com.tech.studentapp.entity.StudentFee;
import com.tech.studentapp.model.StudentFeeRequest;
import com.tech.studentapp.model.StudentFeeResponse;
import com.tech.studentapp.model.StudentRequest;
import com.tech.studentapp.service.StudentFeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fees")
public class StudentFeeController {

    private  final StudentFeeService feeService;

    public StudentFeeController(StudentFeeService feeService) {
        this.feeService = feeService;
    }

    @PostMapping("/savefee")
    public ResponseEntity<List<StudentFee>> createFee(@RequestBody List<StudentFeeRequest> feeRequest){
        List<StudentFee> fee=feeService.createFee(feeRequest);
        return ResponseEntity.ok(fee);
    }

    @GetMapping("/getFee")
    public ResponseEntity<StudentFeeResponse> getFee(@RequestBody StudentRequest studentRequest){
        return  ResponseEntity.ok(feeService.getFeeById(studentRequest.getId()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentFee> updateFee(@PathVariable Long id,
                                                @RequestBody StudentFee studentFee) {
        return ResponseEntity.ok(feeService.updateFee(id, studentFee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFee(@PathVariable Long id) {
        feeService.deleteFee(id);
        return ResponseEntity.ok("Fee record deleted successfully");
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<StudentFee> getFeeByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(feeService.getStudentFeeById(studentId));
    }


}
