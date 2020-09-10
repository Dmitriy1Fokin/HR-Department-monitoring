package ru.fds.hrdepartmentmonitoring.batch.step.tasklet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.fds.hrdepartmentmonitoring.dto.AttendanceSheetDto;
import ru.fds.hrdepartmentmonitoring.feign.HrDepartmentService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
@Qualifier("checkAttendanceTasklet")
public class CheckAttendanceTasklet implements Tasklet, StepExecutionListener {

    private final HrDepartmentService hrDepartmentService;
    private List<AttendanceSheetDto> attendanceSheetDtoList = Collections.emptyList();

    public CheckAttendanceTasklet(HrDepartmentService hrDepartmentService) {
        this.hrDepartmentService = hrDepartmentService;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        Long employeeId = stepExecution.getJobExecution().getJobParameters().getLong("employeeId");
        attendanceSheetDtoList = hrDepartmentService.getAttendanceByEmployee(employeeId).getBody();
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        if(attendanceSheetDtoList.isEmpty()){
            return ExitStatus.FAILED;
        }else{
            List<AttendanceSheetDto> atWorkDays = new ArrayList<>(Collections.emptyList());
            List<AttendanceSheetDto> absenceDays = new ArrayList<>(Collections.emptyList());
            List<AttendanceSheetDto> onSickDays = new ArrayList<>(Collections.emptyList());
            List<AttendanceSheetDto> onVacationDays = new ArrayList<>(Collections.emptyList());

            attendanceSheetDtoList.forEach(attendanceSheetDto -> {
                switch (attendanceSheetDto.getTypeOfAttendance()) {
                    case "AT_WORK":
                        atWorkDays.add(attendanceSheetDto);
                        break;
                    case "ABSENCE":
                        absenceDays.add(attendanceSheetDto);
                        break;
                    case "ON_SICK_LEAVE":
                        onSickDays.add(attendanceSheetDto);
                        break;
                    case "ON_VACATION":
                        onVacationDays.add(attendanceSheetDto);
                        break;
                }
            });

            stepExecution.getJobExecution().getExecutionContext().put("atWorkDaysCollection", atWorkDays);
            stepExecution.getJobExecution().getExecutionContext().put("absenceDaysCollection", absenceDays);
            stepExecution.getJobExecution().getExecutionContext().put("onSickDaysCollection", onSickDays);
            stepExecution.getJobExecution().getExecutionContext().put("onVacationDaysCollection", onVacationDays);
            return ExitStatus.COMPLETED;
        }
    }


}
