package com.cg.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class EmpDTO {

    private Integer empId;

    @NotBlank(message = "Name is required")
    @Pattern(regexp = "[a-zA-Z]+", message = "Name must contain alphabets only")
    @Size(min = 3, max = 25, message = "Name must be between 3 and 25 characters")
    private String empName;

    @NotNull(message = "Salary is required")
    @Min(value = 1000, message = "Salary must be minimum 1000")
    @Max(value = 500000, message = "Salary must be maximum 500000")
    private Double empSal;

    @NotNull(message = "Date of joining is required")
    @FutureOrPresent(message = "Date of joining must be current or future date")
    private LocalDate empDoj;

    @NotBlank(message = "Dept is required")
    @Pattern(regexp = "hr|production", message = "Dept must be 'hr' or 'production'")
    private String deptName;

    public EmpDTO() {}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Double getEmpSal() {
		return empSal;
	}

	public void setEmpSal(Double empSal) {
		this.empSal = empSal;
	}

	public LocalDate getEmpDoj() {
		return empDoj;
	}

	public void setEmpDoj(LocalDate empDoj) {
		this.empDoj = empDoj;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
   
}