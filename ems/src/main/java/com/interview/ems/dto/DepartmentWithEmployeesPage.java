package com.interview.ems.dto;

import java.time.LocalDate;

import org.springframework.data.domain.Page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentWithEmployeesPage {
    private Long id;
    private String name;
    private LocalDate creationDate;
    private Long departmentHeadId;
    private String departmentHeadName;
    private Page<EmployeeResponseDTO> employees;
}
