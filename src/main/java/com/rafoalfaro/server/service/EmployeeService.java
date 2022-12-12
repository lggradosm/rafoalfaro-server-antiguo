package com.rafoalfaro.server.service;

import com.rafoalfaro.server.domain.Employee;
import com.rafoalfaro.server.domain.User;
import com.rafoalfaro.server.repository.EmployeeRepository;
import com.rafoalfaro.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private UserService userService;
    public Employee findEmployeeById(Long id){
        Employee employee = employeeRepository.findById(id).orElse(null);
        Objects.requireNonNull(employee).getUser().setPassword("");
            return employee;
    }

    public Employee findEmployeeByUser(User user){
        return employeeRepository.findEmployeeByUser(user);
    }

    public List<Employee> getEmployees(){
        return employeeRepository.findAll(Sort.by("position").ascending().and(Sort.by("name").ascending()).and(Sort.by("lastName").ascending()))
                .stream().peek(employee -> employee.getUser().setPassword("")).collect(Collectors.toList());
    }

    public Employee saveEmployee(Employee employee){


        User user = employee.getUser();
        user = userService.create(user);
        employee.setUser(user);
        Employee employeeResult = employeeRepository.save(employee);
        employeeResult.getUser().setPassword("");
        return employeeResult;
    }

    public void deleteEmployee(Long id){
        employeeRepository.findById(id).ifPresent(employee -> employeeRepository.delete(employee));
    }

}
