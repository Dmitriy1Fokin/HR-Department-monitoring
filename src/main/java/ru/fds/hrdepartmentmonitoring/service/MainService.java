package ru.fds.hrdepartmentmonitoring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class MainService {

    private final JobLauncher jobLauncher;
    private final Job checkEmployeeJob;
    private final Job insertNewEmployeeJob;

    public MainService(JobLauncher jobLauncher,
                       @Qualifier("checkEmployeeJob") Job checkEmployeeJob,
                       @Qualifier("insertNewEmployeeJob")Job insertNewEmployeeJob) {
        this.jobLauncher = jobLauncher;
        this.checkEmployeeJob = checkEmployeeJob;
        this.insertNewEmployeeJob = insertNewEmployeeJob;
    }

    public void getStatAboutEmployee(Long employeeId){
        try {
            JobParameters jobParameters = new JobParametersBuilder().addLong("employeeId", employeeId).toJobParameters();
            jobLauncher.run(checkEmployeeJob,jobParameters);
        } catch (JobInstanceAlreadyCompleteException e){
            log.info("Job is finished for employee with id: {}", employeeId);
        } catch (JobRestartException | JobExecutionAlreadyRunningException | JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }

    public void insertEmployee(String pathToFile) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        log.info("insertEmployee. Path: {}", pathToFile);
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("pathToFile", pathToFile)
                .addDate("startTime", new Date())
                .toJobParameters();
        jobLauncher.run(insertNewEmployeeJob, jobParameters);
    }
}
