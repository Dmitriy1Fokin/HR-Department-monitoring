package ru.fds.hrdepartmentmonitoring.batch.step.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Qualifier("readLinesStepListener")
public class ReadLinesStepListener implements StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("!!!!!!!!!!!!!!!!!!!!!!Step {} Started!!!!!!!!!!!!!!!!!!!!!!", stepExecution.getStepName());
        stepExecution
                .getExecutionContext()
                .entrySet()
                .forEach(stringObjectEntry -> log.info("{}={}", stringObjectEntry.getKey(), stringObjectEntry.getValue()));
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("!!!!!!!!!!!!!!!!!!!!!!Step {} finished!!!!!!!!!!!!!!!!!!!!!!", stepExecution.getStepName());
        stepExecution
                .getExecutionContext()
                .entrySet()
                .forEach(stringObjectEntry -> log.info("{}={}", stringObjectEntry.getKey(), stringObjectEntry.getValue()));
        return ExitStatus.COMPLETED;
    }
}
