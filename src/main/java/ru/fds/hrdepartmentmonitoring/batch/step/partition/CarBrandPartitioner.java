package ru.fds.hrdepartmentmonitoring.batch.step.partition;


import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Qualifier("carBrandPartitioner")
public class CarBrandPartitioner implements Partitioner {

    private final DataSource dataSource;

    public CarBrandPartitioner(@Qualifier("carDataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Map<String, ExecutionContext> map = new HashMap<>(gridSize);

        List<String> carBrands = jdbcTemplate.queryForList("select distinct(c.car) from car c", String.class);

        carBrands.forEach(s -> {
            ExecutionContext context = new ExecutionContext();
            context.putString("brandCar", s);
            map.put("PARTITION_KEY_" + s, context);
        });

        return map;
    }
}
