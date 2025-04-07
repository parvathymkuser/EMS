package com.interview.ems.mapper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.interview.ems.dto.DepartmentRequestDTO;
import com.interview.ems.dto.DepartmentResponseDTO;
import com.interview.ems.dto.DepartmentWithEmployeesDTO;
import com.interview.ems.dto.DepartmentWithEmployeesPage;
import com.interview.ems.dto.EmployeeResponseDTO;
import com.interview.ems.entity.Department;
import com.interview.ems.entity.Employee;

@Component
public class DepartmentMapper {

    public Department toEntity(DepartmentRequestDTO dto, Employee head) {
        return Department.builder()
                .name(dto.getName())
                .creationDate(dto.getCreationDate())
                .departmentHead(head)
                .build();
    }

    public void updateEntity(Department entity, DepartmentRequestDTO dto, Employee head) {
        entity.setName(dto.getName());
        entity.setCreationDate(dto.getCreationDate());
        entity.setDepartmentHead(head);
    }

    public DepartmentResponseDTO toResponseDTO(Department entity) {
        return DepartmentResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .creationDate(entity.getCreationDate())
                .departmentHeadId(entity.getDepartmentHead() != null ? entity.getDepartmentHead().getId() : null)
                .departmentHeadName(entity.getDepartmentHead() != null ? entity.getDepartmentHead().getName() : null)
                .build();
    }

    public DepartmentWithEmployeesDTO toDepartmentWithEmployeesDTO(Department entity) {
        return DepartmentWithEmployeesDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .creationDate(entity.getCreationDate())
                .departmentHeadId(entity.getDepartmentHead() != null ? entity.getDepartmentHead().getId() : null)
                .departmentHeadName(entity.getDepartmentHead() != null ? entity.getDepartmentHead().getName() : null)
                .employees(entity.getEmployees().stream()
                        .map(emp -> EmployeeResponseDTO.builder()
                                .id(emp.getId())
                                .name(emp.getName())
                                .dateOfBirth(emp.getDateOfBirth())
                                .salary(emp.getSalary())
                                .address(emp.getAddress())
                                .title(emp.getTitle())
                                .joiningDate(emp.getJoiningDate())
                                .bonusPercentage(emp.getBonusPercentage())
                                .departmentId(entity.getId())
                                .departmentName(entity.getName())
                                .reportingManagerId(emp.getReportingManager() != null ? emp.getReportingManager().getId() : null)
                                .reportingManagerName(emp.getReportingManager() != null ? emp.getReportingManager().getName() : null)
                                .build())
                        .toList())
                .build();
    }
    public DepartmentWithEmployeesPage toDepartmentWithEmployeesPage(Department department, Page<EmployeeResponseDTO> employeeDTOPage) {
        return DepartmentWithEmployeesPage.builder()
            .id(department.getId())
            .name(department.getName())
            .creationDate(department.getCreationDate())
            .departmentHeadId(department.getDepartmentHead() != null ? department.getDepartmentHead().getId() : null)
            .departmentHeadName(department.getDepartmentHead() != null ? department.getDepartmentHead().getName() : null)
            .employees(employeeDTOPage)
            .build();
    }

}