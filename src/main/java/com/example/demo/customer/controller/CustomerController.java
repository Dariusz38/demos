package com.example.demo.customer.controller;

import com.example.demo.book.service.BookService;
import com.example.demo.customer.dto.CustomerDto;
import com.example.demo.customer.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@AllArgsConstructor
@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    BookService bookService;

    @GetMapping(value = "/customer-accounts/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerAccount(
            @PathVariable final Long customerId) {
        return ResponseEntity.ok(customerService.getCustomer(customerId));
    }

    @PostMapping(value = "/customer-accounts")
    public ResponseEntity<Long> addCustomerAccount(@RequestBody final CustomerDto customerDto) {
        return ResponseEntity.ok(customerService.addCustomer(customerDto));
    }

    @PutMapping("/customer-accounts/{customerId}") //deactivate customer
    public ResponseEntity<Void> updateCustomerAccount(@PathVariable final Long customerId,
                                                      @RequestBody final CustomerDto customerDto) {
        final Long updatedId = customerService.updateCustomerAccount(customerId, customerDto);
        if (updatedId.equals(customerId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.created(URI.create("/customer-accounts/" + updatedId)).build();
        }
    }

    @PutMapping("/customer/{customerId}") //lend or sell
    public ResponseEntity<Long> updateBook(@PathVariable final Long customerId,
                                           @RequestParam final Long bookId,
                                           @RequestParam(value = "bookStatus") final String status) {
        return ResponseEntity.ok(bookService.updateBookStatus(customerId, bookId, status));
    }
}
