package com.tech.studentapp.service;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* com.tech.studentapp.service.StudentService.saveStudent(..))")
    public void log(){
        System.out.println("Aspect call logged");
    }
}
