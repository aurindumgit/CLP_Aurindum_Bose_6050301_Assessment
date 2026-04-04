package com.cg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.EmpDTO;
import com.cg.entities.Employee;
import com.cg.repo.IEmployeeRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private IEmployeeRepo empRepo;
    
    private EmpDTO convertToDto(Employee emp) {
        EmpDTO dto = new EmpDTO();
        dto.setEmpId(emp.getEmpId());
        dto.setEmpName(emp.getEmpName());
        dto.setEmpSal(emp.getEmpSal());
        dto.setEmpDoj(emp.getEmpDoj());
        dto.setDeptName(emp.getDeptName());
        return dto;
    }

    private Employee convertToEntity(Employee emp, EmpDTO dto) {
        emp.setEmpName(dto.getEmpName());
        emp.setEmpSal(dto.getEmpSal());
        emp.setEmpDoj(dto.getEmpDoj());
        emp.setDeptName(dto.getDeptName());
        return emp;
    }

    @Override
    public List<EmpDTO> getAllEmployees() {
        List<Employee> emps = empRepo.findAll();
        List<EmpDTO> dtos = new ArrayList<>();
        for (Employee emp : emps) {
            dtos.add(convertToDto(emp));
        }
        return dtos;
    }

    @Override
    public EmpDTO getEmployeeById(Integer id) {
        Optional<Employee> emp = empRepo.findById(id);
        if (emp.isPresent()) {
            return convertToDto(emp.get());
        }
        return null;
    }

    @Override
    public void updateEmployee(Integer id, EmpDTO empDto) {
        Optional<Employee> existing = empRepo.findById(id);
        if (existing.isPresent()) {
            Employee emp = convertToEntity(existing.get(), empDto);
            empRepo.save(emp);
        }
    }

    @Override
    public void deleteEmployee(Integer id) {
        empRepo.deleteById(id);
    }

}