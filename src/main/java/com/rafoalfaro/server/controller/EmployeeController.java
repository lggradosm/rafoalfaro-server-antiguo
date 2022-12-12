package com.rafoalfaro.server.controller;

import com.rafoalfaro.server.domain.Employee;
import com.rafoalfaro.server.domain.User;
import com.rafoalfaro.server.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employee")
@CrossOrigin(origins = "*")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = (""),method = RequestMethod.GET)
    public ResponseEntity<?> getEmployees(){
        return ResponseEntity.ok(employeeService.getEmployees());
    }

    @GetMapping(value = ("/{id}"))
    public ResponseEntity<?> findEmployeeById(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.findEmployeeById(id));
    }

    @PostMapping(value = ("/create"))
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee){
        return ResponseEntity.ok(employeeService.saveEmployee(employee));
    }

    @PatchMapping(value = ("/update"))
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee){
        return ResponseEntity.ok(employeeService.saveEmployee(employee));
    }

    @DeleteMapping(value = ("/delete/{id}"))
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("user deleted");
    }

}
