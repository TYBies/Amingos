package com.amigocode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CustomerServiceImplementation implements CustomerService{
    private final CustomerRepository customerRepository;
    private final Mapper<Customer,CustomerDTO> customerMapper;
    @Autowired
    public CustomerServiceImplementation(CustomerRepository customerRepository, Mapper<Customer,CustomerDTO> customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomer() {
        return  customerMapper.toDtoList(customerRepository.findAll());
    }

    @Override
    public CustomerDTO getCustomerById(int id) {
        return customerMapper.toDto(customerRepository.getById(id));
    }

    @Override
    public void createCustomer(CustomerDTO customerDTO) {
     customerRepository.save(customerMapper.toEntity(customerDTO));
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDto) {
        Customer myCustomer = customerRepository.findById(customerDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        myCustomer.setAge(customerDto.getAge());
        myCustomer.setEmail(customerDto.getEmail());
        myCustomer.setName(customerDto.getName());
        Customer customer = customerRepository.save(myCustomer);

        return customerMapper.toDto(customer);
    }

    @Override
    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }
}
