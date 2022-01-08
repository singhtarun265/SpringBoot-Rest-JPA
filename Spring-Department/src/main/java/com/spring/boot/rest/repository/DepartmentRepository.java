package com.spring.boot.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.rest.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
  List<Department> findByStatus(String status);
  List<Department> findByDeptNameContaining(String deptName);
}