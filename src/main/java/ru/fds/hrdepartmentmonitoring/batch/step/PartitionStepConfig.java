package ru.fds.hrdepartmentmonitoring.batch.step;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.fds.hrdepartmentmonitoring.dto.CarStat;


@Slf4j
@Configuration
public class PartitionStepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    public PartitionStepConfig(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    @Qualifier
    public Step partitionStep(Partitioner carBrandPartitioner,
                              @Qualifier("slaveStep") Step slaveStep) {
        return stepBuilderFactory.get("partitionStep")
                .partitioner("slaveStep", carBrandPartitioner)
                .step(slaveStep)
                .build();
    }

    @Bean
    @Qualifier
    public Step slaveStep(ItemReader<CarStat> itemReader,
                          ItemWriter<CarStat> itemWriter){
        return stepBuilderFactory.get("slaveStep")
                .<CarStat, CarStat>chunk(10)
                .reader(itemReader)
                .writer(itemWriter)
                .build();
    }

}
