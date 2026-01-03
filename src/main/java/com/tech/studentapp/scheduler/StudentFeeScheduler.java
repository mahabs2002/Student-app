package com.tech.studentapp.scheduler;

import com.tech.studentapp.service.StudentFeeService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StudentFeeScheduler {

    private final StudentFeeService studentFeeService;

    public StudentFeeScheduler(StudentFeeService studentFeeService) {
        this.studentFeeService = studentFeeService;
    }
    @Scheduled(cron="0 0 0 * * *")
    public void markIneligibleStudentCron(){
        studentFeeService.markIneligibleStudent();
    }
}
