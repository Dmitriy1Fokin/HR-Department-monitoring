package ru.fds.hrdepartmentmonitoring;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableBatchProcessing
public class HrDepartmentMonitoringApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrDepartmentMonitoringApplication.class, args);
    }

}
