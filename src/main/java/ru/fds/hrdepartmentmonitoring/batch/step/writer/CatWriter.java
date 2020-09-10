package ru.fds.hrdepartmentmonitoring.batch.step.writer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.fds.hrdepartmentmonitoring.dto.Cat;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class CatWriter{

    @Bean
    @Qualifier
    public JdbcBatchItemWriter<Cat> catJdbcBatchItemWriter(@Qualifier("businessDataSource") DataSource dataSource){
        final JdbcBatchItemWriter<Cat> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql("INSERT INTO cats (name, breed) VALUES (:name, :breed)");
        writer.setDataSource(dataSource);
        return writer;
    }
}
