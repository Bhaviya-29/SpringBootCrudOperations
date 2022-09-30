package com.example.demo2.service;

import com.example.demo2.entity.Employee;
import com.example.demo2.repository.Emp_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class Emp_Service {
    @Autowired
    private Emp_Repo repo;
    @PostConstruct
    public void initDB(){
        List<Employee> emps = IntStream.rangeClosed(1,200)
                .mapToObj(i->new Employee(i,"emp"+i,new Random().nextInt(10)))
                .collect(Collectors.toList());
        repo.saveAll(emps);
    }


    public Employee saveEmp(Employee e){
        return repo.save(e);
    }
    public List<Employee> saveEmps(List<Employee> el){
        return repo.saveAll(el);
    }
    public List<Employee> getEmps(){
        return repo.findAll();
    }
    public Employee getEmpById(int id){
        return repo.findById(id).orElse(null);
    }
    public Employee getEmpByName(String name){
        return repo.findByName(name);
    }
    public String deleteEmpById(int id){
        repo.deleteById(id);
        return "Employee deleted"+id;
    }
    public Employee updateEmp(Employee e){
        Employee existingEmp = repo.findById(e.getId()).orElse(null);
        existingEmp.setName(e.getName());
        existingEmp.setDeptno(e.getDeptno());
        return repo.save(existingEmp);
    }
    public Page<Employee> getEmpsWithPagination(int offset, int pagesize){
        return repo.findAll(PageRequest.of(offset,pagesize));
    }

    



}
