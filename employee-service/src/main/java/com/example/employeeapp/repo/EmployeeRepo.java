package com.example.employeeapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employeeapp.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer>{

}
