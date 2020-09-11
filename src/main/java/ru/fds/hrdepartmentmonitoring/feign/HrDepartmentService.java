package ru.fds.hrdepartmentmonitoring.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.fds.hrdepartmentmonitoring.dto.AttendanceSheetDto;
import ru.fds.hrdepartmentmonitoring.exception.FeignClientFallbackFactory;

import java.util.List;

@FeignClient(name = "${feign_hr_department_api.name}", fallbackFactory = FeignClientFallbackFactory.class)
public interface HrDepartmentService {

    @GetMapping(value = "/attendance/emp/{employeeId}",
            produces = {"application/json"})
    ResponseEntity<List<AttendanceSheetDto>> getAttendanceByEmployee(@PathVariable("employeeId") Long employeeId);
}