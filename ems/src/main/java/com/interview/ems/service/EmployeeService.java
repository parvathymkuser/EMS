package com.interview.ems.service;

import com.interview.ems.dto.EmployeeMiniDTO;
import com.interview.ems.dto.EmployeeRequestDTO;
import com.interview.ems.dto.EmployeeResponseDTO;
import com.interview.ems.entity.Department;
import com.interview.ems.entity.Employee;
import com.interview.ems.exception.CustomException;
import com.interview.ems.mapper.EmployeeMapper;
import com.interview.ems.repository.DepartmentRepository;
import com.interview.ems.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final DepartmentRepository departmentRepository;
	private final EmployeeMapper employeeMapper;

	public EmployeeResponseDTO createEmployee(EmployeeRequestDTO request) {
		Department department = departmentRepository.findById(request.getDepartmentId())
				.orElseThrow(() -> new CustomException("Department not found with ID: " + request.getDepartmentId()));

		Employee reportingManager = null;
		if (request.getReportingManagerId() != null) {
			reportingManager = employeeRepository.findById(request.getReportingManagerId())
					.orElseThrow(() -> new CustomException(
							"Reporting Manager not found with ID: " + request.getReportingManagerId()));
		}

		Employee employee = employeeMapper.toEntity(request, department, reportingManager);
		Employee saved = employeeRepository.save(employee);
		return employeeMapper.toResponseDTO(saved);
	}

	public EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO request) {
		Employee existing = employeeRepository.findById(id)
				.orElseThrow(() -> new CustomException("Employee not found with ID: " + id));

		Department department = departmentRepository.findById(request.getDepartmentId())
				.orElseThrow(() -> new CustomException("Department not found with ID: " + request.getDepartmentId()));

		Employee reportingManager = null;
		if (request.getReportingManagerId() != null) {
			reportingManager = employeeRepository.findById(request.getReportingManagerId())
					.orElseThrow(() -> new CustomException(
							"Reporting Manager not found with ID: " + request.getReportingManagerId()));
		}

		employeeMapper.updateEntity(existing, request, department, reportingManager);
		return employeeMapper.toResponseDTO(employeeRepository.save(existing));
	}

	public Page<EmployeeResponseDTO> getAllEmployees(Pageable pageable) {
		return employeeRepository.findAll(pageable).map(employeeMapper::toResponseDTO);
	}

	public Page<EmployeeMiniDTO> getLookup(Pageable pageable) {
		return employeeRepository.findAll(pageable).map(employeeMapper::toMiniDTO);
	}

	public EmployeeResponseDTO changeDepartment(Long employeeId, Long newDepartmentId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new CustomException("Employee not found with ID: " + employeeId));

		Department newDepartment = departmentRepository.findById(newDepartmentId)
				.orElseThrow(() -> new CustomException("Department not found with ID: " + newDepartmentId));

		if (employee.getDepartment() != null && employee.getDepartment().getId().equals(newDepartmentId)) {
			throw new CustomException("Employee is already in the selected department.");
		}

		employee.setDepartment(newDepartment);

		Employee updatedEmployee = employeeRepository.save(employee);

		return employeeMapper.toDTO(updatedEmployee);
	}

	public EmployeeResponseDTO getEmployeeByID(Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new CustomException("Employee not found with ID: " + id));
		return employeeMapper.toDTO(employee);
	}

}
