package com.spring.boot.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.rest.model.Department;
import com.spring.boot.rest.repository.DepartmentRepository;

@RestController
public class DepartmentController {

  @Autowired
  DepartmentRepository departmentRepository;

  @GetMapping("/departments")
  public ResponseEntity<List<Department>> getAllDepartments(@RequestParam(required = false) String deptName) {
    try {
      List<Department> departments = new ArrayList<Department>();

      if (deptName == null)
        departmentRepository.findAll().forEach(departments::add);
      else
        departmentRepository.findByDeptNameContaining(deptName).forEach(departments::add);

      if (departments.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(departments, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/departments/{id}")
  public ResponseEntity<Department> getDepartmentById(@PathVariable("id") long id) {
    Optional<Department> departmentData = departmentRepository.findById(id);

    if (departmentData.isPresent()) {
      return new ResponseEntity<>(departmentData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/departments")
  public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
    try {
      Department _department = departmentRepository
          .save(new Department(department.getDeptName(), department.getEmpName(), department.getStatus()));
      return new ResponseEntity<>(_department, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/departments/{id}")
  public ResponseEntity<Department> updateDepartment(@PathVariable("id") long id, @RequestBody Department department) {
    Optional<Department> departmentData = departmentRepository.findById(id);

    if (departmentData.isPresent()) {
      Department _department = departmentData.get();
      _department.setDeptName(department.getDeptName());
      _department.setEmpName(department.getEmpName());
      _department.setStatus(department.getStatus());
      return new ResponseEntity<>(departmentRepository.save(_department), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/departments/{id}")
  public ResponseEntity<HttpStatus> deleteDepartment(@PathVariable("id") long id) {
    try {
      departmentRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/departments")
  public ResponseEntity<HttpStatus> deleteAllDepartments() {
    try {
      departmentRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @GetMapping("/departments/{status}")
  public ResponseEntity<List<Department>> findByPublished(@PathVariable("status")String status) {
    try {
      List<Department> departments = departmentRepository.findByStatus(status);

      if (departments.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(departments, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}