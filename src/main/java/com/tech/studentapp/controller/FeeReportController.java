package com.tech.studentapp.controller;

import com.tech.studentapp.service.FeeReportService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class FeeReportController {

    private  final FeeReportService feeReportService;

    public FeeReportController(FeeReportService feeReportService) {
        this.feeReportService = feeReportService;
    }
    @GetMapping("/fees")
    public ResponseEntity<byte[]> getFeeReport() throws Exception{
        byte[] data=feeReportService.generateFeeReport();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION ,"inline; filename=student_fee_report.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(data);
    }
}
