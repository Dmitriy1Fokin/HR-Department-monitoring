package ru.fds.hrdepartmentmonitoring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class Cat {

    private Long id;
    private String name;
    private String breed;
}
