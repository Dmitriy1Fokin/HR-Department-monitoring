package ru.fds.hrdepartmentmonitoring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.fds.hrdepartmentmonitoring.dto.EmployeeDto;

@Slf4j
@Service
public class MessageService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${queue_name.new_employee}")
    private String insetNewEmployeeQueue;

    public MessageService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public Long sendNewEmployee(EmployeeDto employeeDto){
        return (Long) rabbitTemplate.convertSendAndReceive(insetNewEmployeeQueue, employeeDto);
    }
}
