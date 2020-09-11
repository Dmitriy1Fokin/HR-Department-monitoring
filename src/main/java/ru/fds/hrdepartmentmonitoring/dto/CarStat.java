package ru.fds.hrdepartmentmonitoring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class CarStat {

    String brandName;
    Long countUser;
}
