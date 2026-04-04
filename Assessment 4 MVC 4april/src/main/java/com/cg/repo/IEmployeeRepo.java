package com.cg.repo;

import org.springframework.stereotype.Repository;

import com.cg.entities.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IEmployeeRepo extends JpaRepository<Employee, Integer>{

}
