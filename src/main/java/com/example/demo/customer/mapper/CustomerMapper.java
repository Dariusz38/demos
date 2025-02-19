package com.example.demo.customer.mapper;

import com.example.demo.customer.dto.CustomerDto;
import com.example.demo.customer.model.CustomerModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper MAPPER = Mappers.getMapper(CustomerMapper.class);

    CustomerModel mapToModel(CustomerDto customerDto);

    CustomerDto mapToDto(CustomerModel customerModel);
}
