package ru.fds.hrdepartmentmonitoring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.fds.hrdepartmentmonitoring.service.MainService;

@RestController
public class MainController {

        private final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("stat/emp")
    public ResponseEntity<Void> getStatAboutEmp(@RequestParam("employeeId") Long employeeId){
        mainService.getStatAboutEmployee(employeeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
