package com.decodex.br.adapters.out.persistence.mapper;

import com.decodex.br.adapters.out.persistence.entity.BookEntity;
import com.decodex.br.domain.model.Book;

public class BookMapper {

    public Book toDomain(BookEntity entity) {
        return new Book(
            entity.getId(),
            entity.getAuthor(),
            entity.getLaunchDate(),
            entity.getPrice(),
            entity.getTitle()
        );
    }

    public BookEntity toEntity(Book book) {
        BookEntity entity = new BookEntity();
        entity.setId(book.getId());
        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());
        return entity;
    }
}