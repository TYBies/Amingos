package com.amigocode.service;

import com.amigocode.exception.CustomerNotFoundException;
import com.amigocode.mapper.Mapper;
import com.amigocode.model.Customer;
import com.amigocode.model.CustomerDTO;
import com.amigocode.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CustomerServiceImplementation implements CustomerService {
    private final CustomerRepository customerRepository;
    private final Mapper<Customer, CustomerDTO> customerMapper;
    public CustomerServiceImplementation(CustomerRepository customerRepository, Mapper<Customer, CustomerDTO> customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }
    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customerMapper.toDtoList(customers);
    }
    @Override
    public CustomerDTO getCustomerById(int id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        return customerMapper.toDto(customer);
    }
    @Override
    public String createCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
       return String.valueOf(savedCustomer.getId());
    }
    @Override
    public String updateCustomer(Integer id, CustomerDTO customerDto) {
        Customer customer = customerRepository.findById(customerDto.getId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        customer.setAge(customerDto.getAge());
        customer.setEmail(customerDto.getEmail());
        customer.setName(customerDto.getName());
        customerRepository.save(customer);
        return "Success";
    }
    @Override
    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }
}
