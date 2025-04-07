package com.interview.ems.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponseDTO {
    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private Double salary;
    private String address;
    private String title;
    private LocalDate joiningDate;
    private Double bonusPercentage;
    private Long departmentId;
    private String departmentName;
    private Long reportingManagerId;
    private String reportingManagerName;


}
