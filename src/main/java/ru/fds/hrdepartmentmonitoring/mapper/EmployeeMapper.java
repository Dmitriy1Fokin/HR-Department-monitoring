package ru.fds.hrdepartmentmonitoring.mapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import ru.fds.hrdepartmentmonitoring.dto.EmployeeDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
public class EmployeeMapper implements FieldSetMapper<EmployeeDto> {
    @Override
    public EmployeeDto mapFieldSet(FieldSet fieldSet) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String firstname = fieldSet.readString("firstName");
        String lastName = fieldSet.readString("lastName");
        Long departmentId = Long.valueOf(fieldSet.readString("departmentId"));
        Long positionId = Long.valueOf(fieldSet.readString("positionId"));
        LocalDate dateIn = LocalDate.parse(fieldSet.readString("dateIn"), formatter);
        LocalDate dateOut = LocalDate.parse(fieldSet.readString("dateOut"), formatter);

        return new EmployeeDto(null, firstname, lastName, departmentId, positionId, dateIn, dateOut);
    }
}
