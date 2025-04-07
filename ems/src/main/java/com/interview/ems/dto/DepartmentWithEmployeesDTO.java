package com.interview.ems.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentWithEmployeesDTO {
    private Long id;
    private String name;
    private LocalDate creationDate;
    private Long departmentHeadId;
    private String departmentHeadName;
    private List<EmployeeResponseDTO> employees;
}
