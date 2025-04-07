package com.interview.ems.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.interview.ems.dto.DepartmentRequestDTO;
import com.interview.ems.dto.DepartmentResponseDTO;
import com.interview.ems.dto.DepartmentWithEmployeesPage;
import com.interview.ems.response.ApiResponse;
import com.interview.ems.service.DepartmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

	private final DepartmentService departmentService;

	@PostMapping
	public ResponseEntity<ApiResponse<DepartmentResponseDTO>> createDepartment(
			@RequestBody DepartmentRequestDTO request) {
		return ResponseEntity
				.ok(new ApiResponse<>("Department Created successfully", departmentService.createDepartment(request)));

	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<DepartmentResponseDTO>> updateDepartment(@PathVariable Long id,
			@RequestBody DepartmentRequestDTO request) {
		return ResponseEntity.ok(
				new ApiResponse<>("Department Updated successfully", departmentService.updateDepartment(id, request)));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> deleteDepartment(@PathVariable Long id) {
		departmentService.deleteDepartment(id);
		return ResponseEntity.ok(new ApiResponse<>("Department deleted successfully", null));
	}

	@GetMapping
	public ResponseEntity<ApiResponse<Page<DepartmentResponseDTO>>> getDepartments(Pageable pageable) {

		return ResponseEntity.ok(
				new ApiResponse<>("Departments fetched successfully", departmentService.getAllDepartments(pageable)));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<?>> getDepartmentByIdWithPagination(@PathVariable Long id,
			@RequestParam(value = "expand", required = false) String expand, Pageable pageable) {

		if ("employee".equalsIgnoreCase(expand)) {
			DepartmentWithEmployeesPage result = departmentService.getDepartmentWithPaginatedEmployees(id, pageable);
			return ResponseEntity.ok(new ApiResponse<>("Department with employees fetched successfully", result));
		} else {
			DepartmentResponseDTO department = departmentService.getDepartmentById(id);
			return ResponseEntity.ok(new ApiResponse<>("Department fetched successfully", department));
		}
	}

}
