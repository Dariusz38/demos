package com.example.demo.book.controller;

import com.example.demo.book.dto.BookDto;
import com.example.demo.book.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/book/")
    public ResponseEntity<Long> addBook(@RequestBody final BookDto bookDto) {
        return ResponseEntity.ok(bookService.addBook(bookDto));
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<BookDto> getBook(@PathVariable final Long bookId) {
        return ResponseEntity.ok(bookService.getBook(bookId));
    }
}
