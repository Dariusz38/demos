package com.example.demo.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    @NotNull
    String title;
    @NotNull
    String iban;
    @NotNull
    LocalDate dateOfPrint;
    @NotNull
    String edition;
    String author;
    @NotNull
    BigDecimal price;
    String status;
}
