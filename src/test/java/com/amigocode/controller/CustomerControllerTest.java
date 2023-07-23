package com.amigocode.controller;


import com.amigocode.model.CustomerDTO;
import com.amigocode.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {
    @Mock
    private CustomerService customerService;
    @InjectMocks
    private CustomerController customerController;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }
    @Test
    public void testGetCustomers() throws Exception {
        List<CustomerDTO> customers = Arrays.asList(
                new CustomerDTO(1, "John", "Doe",4),
                new CustomerDTO(2, "Jane", "Doe",6)
        );
        Mockito.when(customerService.getAllCustomers()).thenReturn(customers);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(customers)));
        Mockito.verify(customerService, Mockito.times(1)).getAllCustomers();
    }
    @Test
    public void testGetCustomerById() throws Exception {
        int customerId = 1;
        CustomerDTO customer = new CustomerDTO(customerId, "John", "Doe",4);
        Mockito.when(customerService.getCustomerById(customerId)).thenReturn(customer);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/" + customerId))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(customer)));
        Mockito.verify(customerService, Mockito.times(1)).getCustomerById(customerId);
    }
    @Test
    public void testGetCustomerByIdNotFound() throws Exception {
        int customerId = 1;
        Mockito.when(customerService.getCustomerById(customerId)).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/" + customerId))
                .andExpect(status().isNotFound());
        Mockito.verify(customerService, Mockito.times(1)).getCustomerById(customerId);
    }
    @Test
    public void testDeleteCustomer() throws Exception {
        int customerId = 1;
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/customers/" + customerId))
                .andExpect(status().isNoContent());
        Mockito.verify(customerService, Mockito.times(1)).deleteCustomer(customerId);
    }

   /** @Test
    @Disabled
    public void testCreateCustomer() throws Exception {
        CustomerDTO customer = new CustomerDTO(1, "John", "Doe",4);
        CustomerDTO createdCustomer = new CustomerDTO(1, "John", "Doe",4);
      //  String createdCustomer = "Success";
        Mockito.when(customerService.createCustomer(customer)).thenReturn(String.valueOf(createdCustomer));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated());
                //.andExpect(content().json(objectMapper.writeValueAsString(createdCustomer)));
        Mockito.verify(customerService, Mockito.times(1)).createCustomer(customer);
    }

    @Test
    @Disabled
    public void testUpdateCustomer() throws Exception {
        int customerId = 1;
        CustomerDTO customer = new CustomerDTO(customerId, "John", "Doe",4);
        CustomerDTO updatedCustomer = new CustomerDTO(customerId, "Jane", "Doe",5);
        Mockito.when(customerService.updateCustomer(customerId, customer)).thenReturn(String.valueOf(updatedCustomer));
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/customers/" + customerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(updatedCustomer)));
        Mockito.verify(customerService, Mockito.times(1)).updateCustomer(customerId, customer);
    }
    @Test
    @Disabled
    public void testUpdateCustomerNotFound() throws Exception {
        int customerId = 1;
        CustomerDTO customer = new CustomerDTO(customerId, "John", "Doe",4);
        Mockito.when(customerService.updateCustomer(customerId, customer)).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/customers/" + customerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isNotFound());
        Mockito.verify(customerService, Mockito.times(1)).updateCustomer(customerId, customer);
    }*/
}