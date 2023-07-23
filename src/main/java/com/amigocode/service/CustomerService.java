package com.amigocode.service;

import com.amigocode.model.CustomerDTO;

import java.util.List;

public interface CustomerService {
    /**
     * @return
     * Interface for Customer Services
     */
    List<CustomerDTO> getAllCustomers();
    CustomerDTO getCustomerById(int id);
    String createCustomer(CustomerDTO customerDto);
    String updateCustomer(Integer id, CustomerDTO customerDTO);
    //void deleteCustomer(Long id);
    void deleteCustomer(Integer id);
}
