package ru.fds.hrdepartmentmonitoring.batch.step.reader;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;
import ru.fds.hrdepartmentmonitoring.dto.CarStat;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class CarStatReaderConfig {

    @Bean
    @Qualifier
    @StepScope
    public JdbcCursorItemReader<CarStat> carStatReader(@Qualifier("carDataSource") DataSource dataSource,
                                                       @Value("#{stepExecutionContext[brandCar]}") String brandCar,
                                                       RowMapper<CarStat> rowMapper){
        JdbcCursorItemReader<CarStat> reader = new JdbcCursorItemReader<>();
        String sql = "select c.car as brand, count(c.id) as user_count \n" +
                "from car c\n" +
                "where c.car = '" + brandCar + "'\n" +
                "group by 1";

        reader.setSql(sql);
        reader.setDataSource(dataSource);
        reader.setRowMapper(rowMapper);
        return reader;
    }
}
