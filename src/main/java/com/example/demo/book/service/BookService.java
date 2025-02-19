package com.example.demo.book.service;

import com.example.demo.book.dto.BookDto;

public interface BookService {
    Long addBook(BookDto bookDto);

    Long updateBookStatus(Long customerId, Long bookId, String bookStatus);

    BookDto getBook(Long bookId);
}
