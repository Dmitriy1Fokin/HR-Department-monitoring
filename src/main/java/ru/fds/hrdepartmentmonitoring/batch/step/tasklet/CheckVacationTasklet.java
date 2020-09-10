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
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Configuration
@Qualifier("checkVacationTasklet")
public class CheckVacationTasklet implements Tasklet, StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("Start last step[{}]", stepExecution.getStepName());

        log.info("execution context of job={}", stepExecution.getJobExecution().getJobInstance().getJobName());
        stepExecution.getJobExecution().getExecutionContext().entrySet()
                .forEach(stringObjectEntry -> log.info("{}={}", stringObjectEntry.getKey(), stringObjectEntry.getValue()));
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        List<String> executionContextKeys = stepExecution.getJobExecution().getExecutionContext().entrySet()
                .stream().map(Map.Entry::getKey).collect(Collectors.toList());

        executionContextKeys.forEach(stepExecution.getJobExecution().getExecutionContext()::remove);
        log.info("Clean execution context of job={}", stepExecution.getJobExecution().getJobInstance().getJobName());

        return ExitStatus.COMPLETED;
    }


}
