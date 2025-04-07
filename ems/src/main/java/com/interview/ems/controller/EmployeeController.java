package com.interview.ems.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.interview.ems.dto.EmployeeRequestDTO;
import com.interview.ems.dto.EmployeeResponseDTO;
import com.interview.ems.response.ApiResponse;
import com.interview.ems.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeService employeeService;

	@PostMapping
	public ResponseEntity<ApiResponse<EmployeeResponseDTO>> createEmployee(@RequestBody EmployeeRequestDTO request) {
		return ResponseEntity
				.ok(new ApiResponse<>("Employee Created successfully", employeeService.createEmployee(request)));

	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<EmployeeResponseDTO>> updateEmployee(@PathVariable Long id,
			@RequestBody EmployeeRequestDTO request) {
		return ResponseEntity
				.ok(new ApiResponse<>("Employee updated successfully", employeeService.updateEmployee(id, request)));
	}

	@PatchMapping("/{id}/department")
	public ResponseEntity<ApiResponse<EmployeeResponseDTO>> changeEmployeeDepartment(@PathVariable Long id,
			@RequestParam(name = "newDepartmentId", required = true) Long newDepartmentId) {

		return ResponseEntity.ok(new ApiResponse<>("Department updated successfully",
				employeeService.changeDepartment(id, newDepartmentId)));
	}

	@GetMapping
	public ResponseEntity<ApiResponse<Page<?>>> getEmployees(Pageable pageable,
			@RequestParam(value = "lookup", required = false) Boolean lookup) {
		if (Boolean.TRUE.equals(lookup)) {
			return ResponseEntity
					.ok(new ApiResponse<>("Data Fetched successfully", employeeService.getLookup(pageable)));
		} else {
			return ResponseEntity
					.ok(new ApiResponse<>("Data Fetched successfully", employeeService.getAllEmployees(pageable)));

		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<EmployeeResponseDTO>> getEmployeeById(@PathVariable Long id) {
		return ResponseEntity.ok(new ApiResponse<>("Data Fetched successfully", employeeService.getEmployeeByID(id)));
	}

}
