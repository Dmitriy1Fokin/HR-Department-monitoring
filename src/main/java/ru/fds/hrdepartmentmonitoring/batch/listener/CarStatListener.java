package ru.fds.hrdepartmentmonitoring.batch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Slf4j
@Component
@Qualifier("carStatListener")
public class CarStatListener implements JobExecutionListener {

    private final DataSource dataSource;

    public CarStatListener(@Qualifier("carDataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("job={} started. Delete from table car_stat.", jobExecution.getJobInstance().getJobName());
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("delete from car_stat");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("job={} finished", jobExecution.getJobInstance().getJobName());
    }
}
