package ru.fds.hrdepartmentmonitoring.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.stereotype.Service;

@Service
public class MainService {

    private final JobLauncher jobLauncher;
    private final Job job;

    public MainService(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    public void getStatAboutEmployee(Long employeeId){
        try {
            JobParameters jobParameters = new JobParametersBuilder().addLong("employeeId", employeeId).toJobParameters();
            jobLauncher.run(job,jobParameters);
        } catch (JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();
        } catch (JobRestartException e) {
            e.printStackTrace();
        } catch (JobParametersInvalidException e) {
            e.printStackTrace();
        } catch (JobExecutionAlreadyRunningException e) {
            e.printStackTrace();
        }
    }
}
