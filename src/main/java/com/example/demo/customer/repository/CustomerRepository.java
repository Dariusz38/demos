package com.example.demo.customer.repository;

import com.example.demo.customer.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {
    @Transactional
    @Modifying
    @Query("update CustomerModel c set c.name = ?1, c.surname = ?2, c.active = ?3 where c.id = ?4")
    int updateNameAndSurnameAndActiveByIdEquals(String name, String surname, Boolean active, Long id);


    @Override
    CustomerModel getById(Long customerId);
}
