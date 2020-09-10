package ru.fds.hrdepartmentmonitoring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class AttendanceSheetDto  {

    private Long id;
    private Long employeeId;
    private LocalDate workDate;
    private Integer hourAtWork;
    private String typeOfAttendance;

}
