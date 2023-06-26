package com.amigocode;

import java.util.List;

public interface CustomerService {
    /**
     * @return
     * Interface for Customer Services
     */
    List<CustomerDTO> getAllCustomer();
    CustomerDTO getCustomerById(int id);
    void createCustomer(CustomerDTO customerDto);
    CustomerDTO updateCustomer(CustomerDTO customerDTO);
    //void deleteCustomer(Long id);
    void deleteCustomer(Integer id);
}
