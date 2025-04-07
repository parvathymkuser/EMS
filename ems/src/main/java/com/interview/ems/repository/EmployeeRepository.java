package com.interview.ems.repository;

import com.interview.ems.entity.Employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Page<Employee> findByDepartmentId(Long id, Pageable pageable);
}
