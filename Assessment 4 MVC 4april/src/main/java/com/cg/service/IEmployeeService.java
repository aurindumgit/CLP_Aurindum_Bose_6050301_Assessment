package com.cg.service;
import java.util.List;

import com.cg.dto.EmpDTO;

public interface IEmployeeService {
    List<EmpDTO> getAllEmployees();
    EmpDTO getEmployeeById(Integer id);
    void updateEmployee(Integer id, EmpDTO empDto);
    void deleteEmployee(Integer id);
}