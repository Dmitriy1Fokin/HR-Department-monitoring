package ru.fds.hrdepartmentmonitoring.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfig {

    private final JobBuilderFactory jobBuilderFactory;

    public JobConfig(JobBuilderFactory jobBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
    }

    @Bean
    public Job checkEmployee(@Qualifier("getAttendanceByEmployee") Step step1,
                             @Qualifier("checkWorksDays") Step step2,
                             @Qualifier("checkSickLeave") Step step3,
                             @Qualifier("checkVacations") Step step4){
        return jobBuilderFactory.get("checkEmployee")
                .start(step1).on("FAILED").end()
                .from(step1).on("*").to(step2).next(step3).next(step4).end()
                .build();
    }
}
