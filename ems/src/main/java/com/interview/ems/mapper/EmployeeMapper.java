package com.interview.ems.mapper;

import com.interview.ems.dto.*;
import com.interview.ems.entity.Department;
import com.interview.ems.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee toEntity(EmployeeRequestDTO dto, Department department, Employee manager) {
        return Employee.builder()
                .name(dto.getName())
                .dateOfBirth(dto.getDateOfBirth())
                .salary(dto.getSalary())
                .address(dto.getAddress())
                .title(dto.getTitle())
                .joiningDate(dto.getJoiningDate())
                .bonusPercentage(dto.getBonusPercentage())
                .department(department)
                .reportingManager(manager)
                .build();
    }

    public void updateEntity(Employee entity, EmployeeRequestDTO dto, Department department, Employee manager) {
        entity.setName(dto.getName());
        entity.setDateOfBirth(dto.getDateOfBirth());
        entity.setSalary(dto.getSalary());
        entity.setAddress(dto.getAddress());
        entity.setTitle(dto.getTitle());
        entity.setJoiningDate(dto.getJoiningDate());
        entity.setBonusPercentage(dto.getBonusPercentage());
        entity.setDepartment(department);
        entity.setReportingManager(manager);
    }

    public EmployeeResponseDTO toResponseDTO(Employee entity) {
        return EmployeeResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .dateOfBirth(entity.getDateOfBirth())
                .salary(entity.getSalary())
                .address(entity.getAddress())
                .title(entity.getTitle())
                .joiningDate(entity.getJoiningDate())
                .bonusPercentage(entity.getBonusPercentage())
                .departmentId(entity.getDepartment() != null ? entity.getDepartment().getId() : null)
                .departmentName(entity.getDepartment() != null ? entity.getDepartment().getName() : null)
                .reportingManagerId(entity.getReportingManager() != null ? entity.getReportingManager().getId() : null)
                .reportingManagerName(entity.getReportingManager() != null ? entity.getReportingManager().getName() : null)
                .build();
    }

    public EmployeeMiniDTO toMiniDTO(Employee entity) {
        return EmployeeMiniDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public EmployeeResponseDTO toDTO(Employee entity) {
        return EmployeeResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .dateOfBirth(entity.getDateOfBirth())
                .salary(entity.getSalary())
                .address(entity.getAddress())
                .title(entity.getTitle())
                .joiningDate(entity.getJoiningDate())
                .bonusPercentage(entity.getBonusPercentage())
                .departmentId(entity.getDepartment() != null ? entity.getDepartment().getId() : null)
                .departmentName(entity.getDepartment() != null ? entity.getDepartment().getName() : null)
                .reportingManagerId(entity.getReportingManager() != null ? entity.getReportingManager().getId() : null)
                .build();
    }
}
