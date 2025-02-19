package com.example.demo.customer.model;

import com.example.demo.book.model.BookModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringExclude;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CUSTOMER")
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SURNAME")
    private String surname;
    @Column(name = "ACTIVE")
    private Boolean active;
    @OneToMany(mappedBy = "customerModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @ToStringExclude
    private Set<BookModel> bookModels = new HashSet<>();
    @Column(name = "ADDRESS_ID")
    private Long addressId;
}
