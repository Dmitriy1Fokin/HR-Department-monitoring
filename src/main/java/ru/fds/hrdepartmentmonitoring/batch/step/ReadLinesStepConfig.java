package ru.fds.hrdepartmentmonitoring.batch.step;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.fds.hrdepartmentmonitoring.dto.Cat;


@Slf4j
@Configuration
public class ReadLinesStepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    public ReadLinesStepConfig(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    @Qualifier
    public Step readLinesStep(@Qualifier("catReader") ItemReader<Cat> catItemReader,
                              @Qualifier("catProcessor") ItemProcessor<Cat, Cat> catItemProcessor,
                              @Qualifier("catJdbcBatchItemWriter") ItemWriter<Cat> catItemWriter,
                              @Qualifier("readLinesStepListener") StepExecutionListener listener){
        return stepBuilderFactory.get("readLinesStep")
                .listener(listener)
                .<Cat, Cat>chunk(5)
                .reader(catItemReader)
                .processor(catItemProcessor)
                .writer(catItemWriter)
                .build();
    }
}
