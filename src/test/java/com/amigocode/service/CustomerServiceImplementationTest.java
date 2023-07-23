package com.amigocode.service;

import com.amigocode.exception.CustomerNotFoundException;
import com.amigocode.mapper.Mapper;
import com.amigocode.model.Customer;
import com.amigocode.model.CustomerDTO;
import com.amigocode.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
public class CustomerServiceImplementationTest {
    private CustomerServiceImplementation customerService;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private Mapper<Customer, CustomerDTO> customerMapper;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        customerService = new CustomerServiceImplementation(customerRepository, customerMapper);
    }
    @Test
    public void testGetAllCustomers() {
        // Arrange
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1, "John Doe", "john@example.com", 25));
        customers.add(new Customer(2, "Jane Smith", "jane@example.com", 30));
        when(customerRepository.findAll()).thenReturn(customers);
        List<CustomerDTO> expectedCustomerDTOs = new ArrayList<>();
        expectedCustomerDTOs.add(new CustomerDTO(1, "John Doe", "john@example.com", 25));
        expectedCustomerDTOs.add(new CustomerDTO(2, "Jane Smith", "jane@example.com", 30));
        when(customerMapper.toDtoList(customers)).thenReturn(expectedCustomerDTOs);
        // Act
        List<CustomerDTO> actualCustomerDTOs = customerService.getAllCustomers();
        // Assert
        assertEquals(expectedCustomerDTOs, actualCustomerDTOs);
        verify(customerRepository, times(1)).findAll();
        verify(customerMapper, times(1)).toDtoList(customers);
    }
    @Test
    public void testGetCustomerById() {
        // Arrange
        int customerId = 1;
        Customer customer = new Customer(customerId, "John Doe", "john@example.com", 25);
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        CustomerDTO expectedCustomerDTO = new CustomerDTO(customerId, "John Doe", "john@example.com", 25);
        when(customerMapper.toDto(customer)).thenReturn(expectedCustomerDTO);
        // Act
        CustomerDTO actualCustomerDTO = customerService.getCustomerById(customerId);
        // Assert
        assertEquals(expectedCustomerDTO, actualCustomerDTO);
        verify(customerRepository, times(1)).findById(customerId);
        verify(customerMapper, times(1)).toDto(customer);
    }
    @Test
    public void testGetCustomerById_ThrowsException() {
        // Arrange
        int customerId = 1;
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());
        // Act & Assert
        assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerById(customerId));
        verify(customerRepository, times(1)).findById(customerId);
        verify(customerMapper, never()).toDto(any());
    }
    @Test
    public void testCreateCustomer() {
        // Arrange
        CustomerDTO customerDTO = new CustomerDTO(1, "John Doe", "john@example.com", 25);
        Customer customer = new Customer(1, "John Doe", "john@example.com", 25);
        when(customerMapper.toEntity(customerDTO)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);
        // Act
        String customerId = customerService.createCustomer(customerDTO);
        // Assert
        assertEquals(String.valueOf(customer.getId()), customerId);
        verify(customerMapper, times(1)).toEntity(customerDTO);
        verify(customerRepository, times(1)).save(customer);
    }
    @Test
    public void testUpdateCustomer() {
        // Arrange
        CustomerDTO customerDTO = new CustomerDTO(1, "John Doe", "john@example.com", 25);
        Customer customer = new Customer(1, "John Doe", "john@example.com", 25);
        when(customerRepository.findById(customerDTO.getId())).thenReturn(Optional.of(customer));
        // Act
        String result = customerService.updateCustomer(customerDTO.getId(), customerDTO);
        // Assert
        assertEquals("Success", result);
        verify(customerRepository, times(1)).findById(customerDTO.getId());
        verify(customerRepository, times(1)).save(customer);
    }
    @Test
    public void testUpdateCustomer_ThrowsException() {
        // given
        CustomerDTO customerDTO = new CustomerDTO(1, "John Doe", "john@example.com", 25);
        when(customerRepository.findById(customerDTO.getId())).thenReturn(Optional.empty());
        // when
        assertThrows(CustomerNotFoundException.class, () -> customerService.updateCustomer(customerDTO.getId(), customerDTO));
        verify(customerRepository, times(1)).findById(customerDTO.getId());
        verify(customerRepository, never()).save(any());
    }
    @Test
    public void testDeleteCustomer() {
        // Arrange
        int customerId = 1;
        // Act
        customerService.deleteCustomer(customerId);
        // Assert
        verify(customerRepository, times(1)).deleteById(customerId);
    }
}