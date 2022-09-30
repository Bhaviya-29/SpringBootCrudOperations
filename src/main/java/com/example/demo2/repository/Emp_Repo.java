package com.example.demo2.repository;

import com.example.demo2.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Emp_Repo extends JpaRepository<Employee,Integer> {
    Employee findByName(String name);


}
