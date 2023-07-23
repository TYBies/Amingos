package com.amigocode.mapper;

import com.amigocode.model.Customer;
import com.amigocode.model.CustomerDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper implements Mapper<Customer, CustomerDTO> {
    @Override
    public CustomerDTO toDto(Customer customer) {
        return new CustomerDTO(customer.getId(), customer.getName(), customer.getEmail(), customer.getAge());       
       
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
        // return customers.stream()
        //         .map(this::toDto)
        //         .collect(Collectors.toList());

        List<CustomerDTO> dtoList = new ArrayList<>();
        for (Customer customer : customers) {
            dtoList.add(toDto(customer));
        }
        return dtoList;
    }
}