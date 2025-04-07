package com.interview.ems.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeLookupDTO {
    private Long id;
    private String name;
}