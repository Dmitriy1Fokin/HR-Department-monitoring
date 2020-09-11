package ru.fds.hrdepartmentmonitoring.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.fds.hrdepartmentmonitoring.dto.CarStat;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CarStatRowMapper implements RowMapper<CarStat> {
    @Override
    public CarStat mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CarStat(rs.getString("brand"), rs.getLong("user_count"));
    }
}
