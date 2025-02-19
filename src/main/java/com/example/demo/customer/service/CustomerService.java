package com.example.demo.customer.service;

import com.example.demo.customer.dto.CustomerDto;

public interface CustomerService {
    Long addCustomer(CustomerDto customerDto);

    CustomerDto getCustomer(Long customerId);

    Long updateCustomerAccount(Long customerId, CustomerDto customerDto);
}
