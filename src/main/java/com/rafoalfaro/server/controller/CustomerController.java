package com.rafoalfaro.server.controller;

import com.rafoalfaro.server.domain.Customer;
import com.rafoalfaro.server.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "")
    public ResponseEntity<?> getCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer){
        return ResponseEntity.ok(customerService.saveCustomer(customer));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("user was deleted");
    }
}
