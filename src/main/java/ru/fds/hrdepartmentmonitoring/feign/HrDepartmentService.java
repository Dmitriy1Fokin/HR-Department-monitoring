package ru.fds.hrdepartmentmonitoring.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.fds.hrdepartmentmonitoring.dto.AttendanceSheetDto;

import java.util.List;

@FeignClient("${feign_hr_department_api.name}")
public interface HrDepartmentService {

    @GetMapping(value = "/attendance/{attendanceId}",
            produces = { "application/json" })
    ResponseEntity<AttendanceSheetDto> getAttendance(@PathVariable("attendanceId") Long attendanceId);

    @GetMapping(value = "/attendance/emp/{employeeId}",
            produces = { "application/json" })
    ResponseEntity<List<AttendanceSheetDto>> getAttendanceByEmployee(@PathVariable("employeeId") Long employeeId);

    @GetMapping(value = "/dep/total_hour",
            produces = { "application/json" })
    ResponseEntity<Integer> getWorkHoursInDepartment(@RequestParam(value = "departmentId") Long departmentId);




}
