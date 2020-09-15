package ru.fds.hrdepartmentmonitoring.batch.step;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import ru.fds.hrdepartmentmonitoring.dto.EmployeeDto;
import ru.fds.hrdepartmentmonitoring.mapper.EmployeeMapper;


@Slf4j
@Configuration
public class ReadLinesStepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    public ReadLinesStepConfig(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    @Qualifier
    public Step readEmployeeFromFileStep(@Qualifier("reader") ItemReader<EmployeeDto> reader,
                                         @Qualifier("employeeWriter") ItemWriter<EmployeeDto> writer){
        return stepBuilderFactory.get("readEmployeeFromFileStep")
                .<EmployeeDto, EmployeeDto>chunk(1000)
                .reader(reader)
                .writer(writer)
                .build();
    }

    @Bean
    @StepScope
    @Qualifier
    public FlatFileItemReader<EmployeeDto> reader(@Value("#{jobParameters['pathToFile']}") String fileName) {

        return new FlatFileItemReaderBuilder<EmployeeDto>()
                .name("personItemReader")
                .resource(new FileSystemResource(fileName))
                .delimited()
                .names("firstName", "lastName", "departmentId", "positionId", "dateIn", "dateOut")
                .fieldSetMapper(new EmployeeMapper())
                .linesToSkip(1)
                .build();
    }
}
