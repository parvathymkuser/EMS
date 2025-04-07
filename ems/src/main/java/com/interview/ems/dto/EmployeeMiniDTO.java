package com.interview.ems.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeMiniDTO {
    private Long id;
    private String name;
}
