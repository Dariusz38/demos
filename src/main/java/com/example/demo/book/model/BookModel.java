package com.example.demo.book.model;

import com.example.demo.book.enums.BookStatus;
import com.example.demo.customer.model.CustomerModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@ToString
@Table(name = "BOOK")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookModel implements Serializable {
    BookStatus bookStatus;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String iban;
    @NotNull
    private LocalDate dateOfPrint;
    @NotNull
    private String edition;
    @NotNull
    private String author;
    @NotNull
    private BigDecimal price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    private CustomerModel customerModel;
}
