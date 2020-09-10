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

import java.util.Collections;
import java.util.List;

@Slf4j
@Component
@Qualifier("checkAtWorkDaysTasklet")
public class CheckAtWorkDaysTasklet implements Tasklet, StepExecutionListener {

    private List<AttendanceSheetDto> atWorkDays = Collections.emptyList();

    @Override
    public void beforeStep(StepExecution stepExecution) {
        atWorkDays = (List) stepExecution.getJobExecution().getExecutionContext().get("atWorkDaysCollection");
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        atWorkDays.forEach(attendanceSheetDto -> log.info("vacation day: {}", attendanceSheetDto));
        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        stepExecution.getJobExecution().getExecutionContext().remove("atWorkDaysCollection");

        return ExitStatus.COMPLETED;
    }


}
