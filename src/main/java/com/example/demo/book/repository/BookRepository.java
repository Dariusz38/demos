package com.example.demo.book.repository;

import com.example.demo.book.enums.BookStatus;
import com.example.demo.book.model.BookModel;
import com.example.demo.customer.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long> {
    BookModel findByIdEquals(Long id);

    @Transactional
    @Modifying
    @Query("update BookModel b set b.bookStatus = ?1, b.customerModel = ?2 where b.id = ?3")
    int updateBookStatusAndCustomerModelByIdEquals(BookStatus bookStatus, CustomerModel customerModel, Long id);
}
