package ru.fds.hrdepartmentmonitoring.batch.step;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Configuration
public class EmployeeStepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    public EmployeeStepConfig(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    @Qualifier("getAttendanceByEmployee")
    public Step getAttendanceByEmployee(@Qualifier("checkAttendanceTasklet") Tasklet checkAttendanceTasklet){
        return stepBuilderFactory.get("getAttendanceByEmployee")
                .tasklet(checkAttendanceTasklet)
                .build();
    }

    @Bean
    @Qualifier("checkWorksDays")
    public Step checkWorksDays(@Qualifier("checkAtWorkDaysTasklet") Tasklet checkAtWorkDaysTasklet){
        return stepBuilderFactory.get("checkWorksDays")
                .tasklet(checkAtWorkDaysTasklet)
                .build();
    }

    @Bean
    @Qualifier("checkSickLeave")
    public Step checkSickLeave(){
        return stepBuilderFactory.get("checkSickLeave")
                .tasklet(checkSickLeaveTasklet())
                .build();
    }

    private Tasklet checkSickLeaveTasklet(){
        return (contribution, chunkContext) -> RepeatStatus.FINISHED;
    }

    @Bean
    @Qualifier("checkVacations")
    public Step checkVacations(){
        return stepBuilderFactory.get("checkVacations")
                .tasklet(checkVacationTasklet())
                .build();
    }
    private Tasklet checkVacationTasklet(){
        return (contribution, chunkContext) -> RepeatStatus.FINISHED;
    }


}
