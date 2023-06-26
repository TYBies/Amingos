package com.amigocode;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper implements Mapper<Customer,CustomerDTO>{
    @Override
    public CustomerDTO toDto(Customer customer) {
        CustomerDTO customerDto = new CustomerDTO();
        customerDto.setName(customer.getName());
        customerDto.setId(customer.getId());
        customerDto.setAge(customer.getAge());
        customerDto.setEmail(customer.getEmail());
        return customerDto;
    }

    @Override
    public Customer toEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setId(customerDTO.getId());
        customer.setAge(customerDTO.getAge());
        customer.setEmail(customerDTO.getEmail());
        return customer;
    }

    @Override
    public List<Customer> toEntityList(List<CustomerDTO> customerDTOS) {

        return customerDTOS.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerDTO> toDtoList(List<Customer> customers) {
        return customers.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}