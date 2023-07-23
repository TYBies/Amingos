package com.amigocode.controller;

import com.amigocode.model.CustomerDTO;
import com.amigocode.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getCustomers() {
        List<CustomerDTO> customerDtos = customerService.getAllCustomers();
        return ResponseEntity.ok(customerDtos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable int id) {
        CustomerDTO customerDto = customerService.getCustomerById(id);
        if (customerDto != null) {
            return ResponseEntity.ok(customerDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody CustomerDTO customerDTO) {
        String createdCustomer = customerService.createCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customerId") Integer id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{customerId}")
    public ResponseEntity<String> updateCustomer(@PathVariable("customerId") Integer id, @RequestBody CustomerDTO customerDTO) {
        String updatedCustomer = customerService.updateCustomer(id, customerDTO);
        if (updatedCustomer != null) {
            return ResponseEntity.ok(updatedCustomer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}