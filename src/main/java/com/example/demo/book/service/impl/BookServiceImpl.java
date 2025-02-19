package com.example.demo.book.service.impl;

import com.example.demo.book.dto.BookDto;
import com.example.demo.book.enums.BookStatus;
import com.example.demo.book.mapper.BookMapper;
import com.example.demo.book.repository.BookRepository;
import com.example.demo.book.service.BookService;
import com.example.demo.customer.model.CustomerModel;
import com.example.demo.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Long addBook(final BookDto bookDto) {
        return bookRepository.save(BookMapper.MAPPER.mapDtoToModel(bookDto)).getId();
    }

    @Override
    public Long updateBookStatus(final Long customerId, final Long bookId, final String bookStatus) {
        final CustomerModel customerModel = customerRepository.getById(customerId);
        return Long.valueOf(bookRepository.updateBookStatusAndCustomerModelByIdEquals(
                BookStatus.valueOf(bookStatus), customerModel, bookId));
    }

    @Override

    public BookDto getBook(final Long bookId) {
        return BookMapper.MAPPER.mapModelToDto(bookRepository.findByIdEquals(bookId));
    }
}
