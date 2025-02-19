package com.example.demo.book.mapper;

import com.example.demo.book.dto.BookDto;
import com.example.demo.book.enums.BookStatus;
import com.example.demo.book.model.BookModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
    BookMapper MAPPER = Mappers.getMapper(BookMapper.class);

    @Named("bookStatusToString")
    static String bookStatusToString(final BookStatus bookStatus) {
        return bookStatus.name();
    }

    BookModel mapDtoToModel(BookDto bookDto);

    @Mapping(target = "status", source = "bookStatus", qualifiedByName = "bookStatusToString")
    BookDto mapModelToDto(BookModel bookModel);
}
