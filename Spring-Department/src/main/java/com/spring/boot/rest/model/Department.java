package com.spring.boot.rest.model;

import javax.persistence.*;

@Entity
@Table(name = "departments")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "deptName")
	private String deptName;

	@Column(name = "empName")
	private String empName;

	@Column(name = "status")
	private String status;

	public Department() {

	}

	public long getId() {
		return id;
	}
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

	@Override
	public String toString() {
		return "Department [id=" + id + ", deptName=" + deptName + ", empName=" + empName + ", status=" + status + "]";
	}

	public Department(String deptName, String empName, String status) {
		this.deptName = deptName;
		this.empName = empName;
		this.status = status;
	}

}