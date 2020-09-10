package ru.fds.hrdepartmentmonitoring.batch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class ReadLineJobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("!!!!!!!!!!!!!!!!!!!!read lines job start!!!!!!!!!!!!!!!!!!!!");
        log.info("job parameters:");
        jobExecution.getJobParameters().getParameters().forEach((s, jobParameter) -> log.info("{}={}", s, jobParameter));
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("!!!!!!!!!!!!!!!!!!!!read lines job finished!!!!!!!!!!!!!!!!!!!!");
        log.info("job parameters:");
        jobExecution.getJobParameters().getParameters().forEach((s, jobParameter) -> log.info("{}={}", s, jobParameter));
    }
}
