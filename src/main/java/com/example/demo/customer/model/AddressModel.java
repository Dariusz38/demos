package com.example.demo.customer.model;


import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "ADDRESS")
@NoArgsConstructor
public class AddressModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "CITY")
    String city;
    @Column(name = "STREET")
    String street;
    @Column(name = "CODE")
    String code;
}
