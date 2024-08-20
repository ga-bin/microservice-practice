package com.eazybytes.accounts.mapper;

import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.CustomerDetailsDto;
import com.eazybytes.accounts.entity.Customer;

public class CustomerMapper {
    
    public static CustomerDto mapTocustomerDto(Customer customer, CustomerDto customerDto) {
        
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static CustomerDetailsDto mapToCustomerDetailsDTO(Customer customer, CustomerDetailsDto customerDetailsDTO) {
        
        customerDetailsDTO.setName(customer.getName());
        customerDetailsDTO.setEmail(customer.getEmail());
        customerDetailsDTO.setMobileNumber(customer.getMobileNumber());
        return customerDetailsDTO;
    }


    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }
}
