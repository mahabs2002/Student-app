package com.tech.studentapp.service;

import com.tech.studentapp.entity.StudentFee;
import com.tech.studentapp.repository.StudentFeeRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FeeReportServiceImpl implements  FeeReportService{

    private final StudentFeeRepository feeRepo;

    public FeeReportServiceImpl(StudentFeeRepository feeRepo) {
        this.feeRepo = feeRepo;
    }

    @Override
    public byte[] generateFeeReport() throws Exception {
        List<StudentFee> feeList=feeRepo.findAll();
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(feeList);
        Map<String, Object> parameters = new HashMap<>();

        JasperReport jasperReport= JasperCompileManager.compileReport(getClass().getResourceAsStream("/reports/Fees.jrxml"));
        JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,parameters,dataSource);
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    @Override
    public List<StudentFee> getAllFees() {
        return feeRepo.findAll();
    }
}
