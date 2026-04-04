package com.cg.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.cg.dto.EmpDTO;
import com.cg.service.IEmployeeService;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private IEmployeeService empService;

    @GetMapping("/viewall")
    public String viewAll(Model model) {
        List<EmpDTO> employees = empService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "viewall";
    }

    @GetMapping("/edit/{eid}")
    public String showEditPage(@PathVariable Integer eid, Model model) {
        EmpDTO empDto = empService.getEmployeeById(eid);
        if (empDto != null) {
            model.addAttribute("empDto", empDto);
        } 
        else {
            model.addAttribute("errorMessage", "Employee not found with ID: " + eid);
            model.addAttribute("empDto", new EmpDTO());
        }
        return "editEmp";
    }

    @PostMapping("/edit/{eid}")
    public String updateEmployee(@PathVariable Integer eid, @Valid @ModelAttribute("empDto") EmpDTO empDto, BindingResult result, Model model) {
    	if (result.hasErrors()) {
    	    empDto.setEmpId(eid);
    	    return "editEmp";
    	}
        empService.updateEmployee(eid, empDto);
        model.addAttribute("employees", empService.getAllEmployees());
        model.addAttribute("successMessage", "Employee Edited successfully!");
        return "viewall";
    }

    @GetMapping("/delete/{eid}")
    public String deleteEmployee(@PathVariable Integer eid, Model model) {
        empService.deleteEmployee(eid);
        model.addAttribute("employees", empService.getAllEmployees());
        model.addAttribute("successMessage", "Employee Deleted successfully!");
        return "viewall";
    }
}