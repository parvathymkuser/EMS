package com.interview.ems.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentResponseDTO {
    private Long id;
    private String name;
    private LocalDate creationDate;
    private Long departmentHeadId;
    private String departmentHeadName;
}
