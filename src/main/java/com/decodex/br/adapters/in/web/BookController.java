package com.decodex.br.adapters.in.web;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.decodex.br.domain.model.Book;
import com.decodex.br.domain.port.in.BookUseCase;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookUseCase useCase;

    public BookController(BookUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public List<Book> findAll() {
        return useCase.findAll();
    }

    @GetMapping("/{id}")
    public Book findById(@PathVariable Long id) {
        return useCase.findById(id);
    }

    @PostMapping
    public Book create(@RequestBody Book book) {
        return useCase.create(book);
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book book) {
        return useCase.update(id, book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        useCase.delete(id);
    }
}