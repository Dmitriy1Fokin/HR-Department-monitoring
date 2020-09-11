package ru.fds.hrdepartmentmonitoring.batch.step.writer;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.fds.hrdepartmentmonitoring.dto.CarStat;

import javax.sql.DataSource;

@Configuration
public class CarStatWriterConfig {

    @Bean
    @Qualifier
    @StepScope
    public JdbcBatchItemWriter<CarStat> carStatWriter(@Qualifier("carDataSource") DataSource dataSource){
        final JdbcBatchItemWriter<CarStat> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());

        String sql = "INSERT INTO car_stat (brand, count_user) VALUES (:brandName, :countUser)";
        writer.setSql(sql);
        writer.setDataSource(dataSource);
        return writer;
    }
}
