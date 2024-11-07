package com.example.book.service;

import java.util.List;

import com.example.book.dto.BookDTO;
import com.example.book.dto.CategoryDTO;
import com.example.book.dto.PublisherDTO;
import com.example.book.entity.Book;
import com.example.book.entity.Category;
import com.example.book.entity.Publisher;

public interface BookService {

    Long create(BookDTO dto);

    BookDTO getRow(Long id);

    List<BookDTO> getList();

    Long update(BookDTO dto);

    void delete(Long id);

    List<CategoryDTO> getCateList();

    List<PublisherDTO> getPubList();

    public default BookDTO entityToDto(Book entity) {
        return BookDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .writer(entity.getWriter())
                .categoryName(entity.getCategory().getName())
                .publisherName(entity.getPublisher().getName())
                .price(entity.getPrice())
                .salePrice(entity.getSalePrice())
                .createDateTime(entity.getCreateDateTime())
                .lastModifyDateTime(entity.getLastModifyDateTime())
                .build();
    }

    public default Book dtoToEntity(BookDTO dto) {

        return Book.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .writer(dto.getWriter())
                .price(dto.getPrice())
                .salePrice(dto.getSalePrice())
                .category(Category.builder().id(Long.parseLong(dto.getCategoryName())).build())
                .publisher(Publisher.builder().id(Long.parseLong(dto.getPublisherName())).build())
                .build();
    }

    public default CategoryDTO cEntityToDto(Category entity) {
        return CategoryDTO.builder()
                .id(entity.getId())
                .categoryName(entity.getName())
                .build();
    }

    public default Category cDtoToEntity(CategoryDTO cDto) {
        return Category.builder()
                .id(cDto.getId())
                .name(cDto.getCategoryName())
                .build();
    }

    public default PublisherDTO pEntityToDto(Publisher entity) {
        return PublisherDTO.builder()
                .id(entity.getId())
                .publisherName(entity.getName())
                .build();
    }

    public default Publisher pDtoToEntity(PublisherDTO pDto) {
        return Publisher.builder()
                .id(pDto.getId())
                .name(pDto.getPublisherName())
                .build();
    }
}
