package ru.fds.hrdepartmentmonitoring.batch.step.writer;


import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.fds.hrdepartmentmonitoring.dto.EmployeeDto;
import ru.fds.hrdepartmentmonitoring.service.MessageService;

import java.util.List;

@Slf4j
@Component
@Qualifier("employeeWriter")
public class EmployeeWriter implements ItemWriter<EmployeeDto> {

    private final MessageService messageService;

    public EmployeeWriter(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void write(List<? extends EmployeeDto> items) {
        items.forEach(employeeDto -> {
            log.info("send employee:{}", employeeDto);
            Long id = messageService.sendNewEmployee(employeeDto);
            log.info("receive new id: {}", id);
        });
    }
}
