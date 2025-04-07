package com.interview.ems.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentRequestDTO {
    private String name;
    private LocalDate creationDate;
    private Long departmentHeadId;
}
