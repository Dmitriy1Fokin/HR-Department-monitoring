package ru.fds.hrdepartmentmonitoring.exception;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.fds.hrdepartmentmonitoring.dto.AttendanceSheetDto;
import ru.fds.hrdepartmentmonitoring.feign.HrDepartmentService;

import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class FeignClientFallbackFactory implements FallbackFactory<HrDepartmentService> {
    @Override
    public HrDepartmentService create(Throwable throwable) {

        return new HrDepartmentService() {
            @Override
            public ResponseEntity<List<AttendanceSheetDto>> getAttendanceByEmployee(Long employeeId) {
                log.info(throwable.getMessage());
                return ResponseEntity.badRequest().body(Collections.emptyList());
            }
        };
    }
}
