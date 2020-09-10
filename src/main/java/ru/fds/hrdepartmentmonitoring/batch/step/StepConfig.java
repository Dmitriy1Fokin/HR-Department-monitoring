package ru.fds.hrdepartmentmonitoring.batch.step;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.fds.hrdepartmentmonitoring.batch.step.tasklet.CheckAtWorkDaysTasklet;
import ru.fds.hrdepartmentmonitoring.batch.step.tasklet.CheckAttendanceTasklet;
import ru.fds.hrdepartmentmonitoring.feign.HrDepartmentService;


@Slf4j
@Configuration
public class StepConfig {

    private final StepBuilderFactory stepBuilderFactory;
    private final HrDepartmentService hrDepartmentService;

    public StepConfig(StepBuilderFactory stepBuilderFactory,
                      HrDepartmentService hrDepartmentService) {
        this.stepBuilderFactory = stepBuilderFactory;
        this.hrDepartmentService = hrDepartmentService;
    }

    @Bean
    @Qualifier("getAttendanceByEmployee")
    public Step getAttendanceByEmployee(){
        return stepBuilderFactory.get("getAttendanceByEmployee")
                .tasklet(checkAttendanceTasklet())
                .build();
    }

    @Bean
    public Tasklet checkAttendanceTasklet(){
        return new CheckAttendanceTasklet(hrDepartmentService);
    }

    @Bean
    @Qualifier("checkWorksDays")
    public Step checkWorksDays(){
        return stepBuilderFactory.get("checkWorksDays")
                .tasklet(checkAtWorkDaysTasklet())
                .build();
    }

    @Bean
    public Tasklet checkAtWorkDaysTasklet(){
        return new CheckAtWorkDaysTasklet();
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
