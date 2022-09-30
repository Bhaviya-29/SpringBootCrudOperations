package com.example.demo2.controller;

import com.example.demo2.dto.APIResponse;
import com.example.demo2.entity.Employee;
import com.example.demo2.service.Emp_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Emp_Controller {
    @Autowired
    private Emp_Service eservice;
    //create
    @PostMapping("/addEmployee")
    public Employee addEmp(@RequestBody Employee e){
        return eservice.saveEmp(e);
    }
    @PostMapping("/addEmployees")
    public List<Employee> addEmps(@RequestBody List<Employee> e){
        return eservice.saveEmps(e);
    }
    @GetMapping("/employees")
    public APIResponse<List<Employee>> findAllEmps(){
        List<Employee> emps = eservice.getEmps();
        return new APIResponse<>(emps.size(),emps);
    }
    @GetMapping("/employees/{offset}/{pagesize}")
    public APIResponse<Page<Employee>> findAllEmpsWithPagination(@PathVariable int offset,@PathVariable int pagesize){
        Page<Employee> emps = eservice.getEmpsWithPagination(offset,pagesize);
        return new APIResponse<>(emps.getSize(),emps);
    }
    @GetMapping("/employee/{id}")
    public Employee findEmpById(@PathVariable int id){
        return eservice.getEmpById(id);
    }
    @GetMapping("/employee/name/{name}")
    public Employee findEmpByName(@PathVariable String name){
        return eservice.getEmpByName(name);
    }
    @PutMapping("/update")
    public Employee updateEmp(@RequestBody Employee e){
        return eservice.updateEmp(e);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteEmpById(@PathVariable int id){
        return eservice.deleteEmpById(id);
    }





}
