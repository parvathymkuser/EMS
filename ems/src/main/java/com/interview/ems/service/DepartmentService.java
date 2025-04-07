package com.interview.ems.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.interview.ems.dto.DepartmentRequestDTO;
import com.interview.ems.dto.DepartmentResponseDTO;
import com.interview.ems.dto.DepartmentWithEmployeesDTO;
import com.interview.ems.dto.DepartmentWithEmployeesPage;
import com.interview.ems.dto.EmployeeResponseDTO;
import com.interview.ems.entity.Department;
import com.interview.ems.entity.Employee;
import com.interview.ems.exception.CustomException;
import com.interview.ems.mapper.DepartmentMapper;
import com.interview.ems.mapper.EmployeeMapper;
import com.interview.ems.repository.DepartmentRepository;
import com.interview.ems.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentService {

	private final DepartmentRepository departmentRepository;
	private final EmployeeRepository employeeRepository;
	private final DepartmentMapper departmentMapper;
	private final EmployeeMapper employeeMapper;

	public DepartmentResponseDTO createDepartment(DepartmentRequestDTO request) {
		Employee head = null;
		if (request.getDepartmentHeadId() != null) {
			head = employeeRepository.findById(request.getDepartmentHeadId()).orElseThrow(
					() -> new CustomException("Department Head not found with ID: " + request.getDepartmentHeadId()));
		}

		Department department = departmentMapper.toEntity(request, head);
		return departmentMapper.toResponseDTO(departmentRepository.save(department));
	}

	public DepartmentResponseDTO updateDepartment(Long id, DepartmentRequestDTO request) {
		Department dept = departmentRepository.findById(id)
				.orElseThrow(() -> new CustomException("Department not found with ID: " + id));

		Employee head = null;
		if (request.getDepartmentHeadId() != null) {
			head = employeeRepository.findById(request.getDepartmentHeadId()).orElseThrow(
					() -> new CustomException("Department Head not found with ID: " + request.getDepartmentHeadId()));
		}

		departmentMapper.updateEntity(dept, request, head);
		return departmentMapper.toResponseDTO(departmentRepository.save(dept));
	}

	public void deleteDepartment(Long id) {
		Department dept = departmentRepository.findById(id)
				.orElseThrow(() -> new CustomException("Department not found with ID: " + id));

		if (!dept.getEmployees().isEmpty()) {
			throw new CustomException("Cannot delete department with assigned employees");
		}

		departmentRepository.deleteById(id);
	}

	public Page<DepartmentResponseDTO> getAllDepartments(Pageable pageable) {
		return departmentRepository.findAll(pageable).map(departmentMapper::toResponseDTO);
	}

	public Page<DepartmentWithEmployeesDTO> getAllDepartmentsWithEmployees(Pageable pageable) {
		return departmentRepository.findAll(pageable).map(departmentMapper::toDepartmentWithEmployeesDTO);
	}

	public DepartmentWithEmployeesPage getDepartmentWithPaginatedEmployees(Long id, Pageable pageable) {
		Department department = departmentRepository.findById(id)
				.orElseThrow(() -> new CustomException("Department not found with ID: " + id));

		Page<Employee> employeePage = employeeRepository.findByDepartmentId(id, pageable);
		Page<EmployeeResponseDTO> employeeDTOPage = employeePage.map(employeeMapper::toDTO);

		return departmentMapper.toDepartmentWithEmployeesPage(department, employeeDTOPage);

	}

	public DepartmentResponseDTO getDepartmentById(Long id) {
		Department department = departmentRepository.findById(id)
				.orElseThrow(() -> new CustomException("Department not found with ID: " + id));
		return departmentMapper.toResponseDTO(department);
	}

}
