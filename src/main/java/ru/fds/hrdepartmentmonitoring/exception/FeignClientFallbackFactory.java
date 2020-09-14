package ru.fds.hrdepartmentmonitoring.exception;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.fds.hrdepartmentmonitoring.feign.HrDepartmentService;

import java.util.Collections;

@Slf4j
@Component
public class FeignClientFallbackFactory implements FallbackFactory<HrDepartmentService> {
    @Override
    public HrDepartmentService create(Throwable throwable) {

        return employeeId -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList());
    }
}
