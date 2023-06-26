package com.amigocode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping
    public ResponseEntity <List<CustomerDTO>> getCustomers(){
        List<CustomerDTO> customerDtos = customerService.getAllCustomer();
        return new ResponseEntity<>(customerDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable int id) {
        CustomerDTO customerDto = customerService.getCustomerById(id);
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<CustomerDTO> postCustomerRequest(@RequestBody CustomerDTO customerDTO ){
       customerService.createCustomer(customerDTO);
        return new ResponseEntity<>(customerDTO, HttpStatus.CREATED);
    }
    @DeleteMapping("{customerId}")
    public ResponseEntity<Void> deleteCustomerRequest(@PathVariable("customerId") Integer id ){
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("{customerId}")
    public ResponseEntity<CustomerDTO>  updateCustomer( @PathVariable("customerId") Integer id, @RequestBody CustomerDTO customerDTO){
        customerDTO.setId(id);
        customerService.updateCustomer(customerDTO);
        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

}