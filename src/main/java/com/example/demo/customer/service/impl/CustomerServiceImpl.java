package com.example.demo.customer.service.impl;

import com.example.demo.customer.dto.CustomerDto;
import com.example.demo.customer.mapper.CustomerMapper;
import com.example.demo.customer.model.CustomerModel;
import com.example.demo.customer.repository.CustomerRepository;
import com.example.demo.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Long addCustomer(final CustomerDto customerDto) {
        try {
            return customerRepository.save(CustomerMapper.MAPPER.mapToModel(customerDto)).getId();
        } catch (final HttpMessageNotReadableException ex) {
            throw new RuntimeException();
        }
    }

    @Override
    public CustomerDto getCustomer(final Long customerId) {
        return CustomerMapper.MAPPER.mapToDto(customerRepository.getById(customerId));
    }

    @Override
    public Long updateCustomerAccount(final Long customerId, final CustomerDto customerDto) {
        final CustomerModel customerModel = CustomerMapper.MAPPER.mapToModel(customerDto);
        return Long.valueOf(customerRepository.updateNameAndSurnameAndActiveByIdEquals(customerModel.getName(), customerModel.getSurname(), customerModel.getActive(), customerId));
    }
}
