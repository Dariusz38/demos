package com.example.demo.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private String name;
    private String surname;
    private Boolean active;
    @NotNull
    private Long addressId;
}
