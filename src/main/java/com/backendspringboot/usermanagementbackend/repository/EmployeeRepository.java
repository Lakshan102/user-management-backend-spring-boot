package com.backendspringboot.usermanagementbackend.repository;

import com.backendspringboot.usermanagementbackend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
