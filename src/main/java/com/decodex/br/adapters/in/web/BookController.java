package com.decodex.br.adapters.in.web;


import java.time.LocalDate;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.decodex.br.application.dto.book.BookCreateDTO;
import com.decodex.br.application.dto.book.BookResponseDTO;
import com.decodex.br.application.dto.book.BookUpdateDTO;
import com.decodex.br.application.mapper.BookDTOMapper;
import com.decodex.br.domain.filter.BookFilter;
import com.decodex.br.domain.model.Book;
import com.decodex.br.domain.pagination.PageRequest;
import com.decodex.br.domain.pagination.PageResult;
import com.decodex.br.domain.port.in.BookUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookUseCase useCase;

    public BookController(BookUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public PageResult<BookResponseDTO> findAll(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) LocalDate launchDate,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        BookFilter filter = new BookFilter(author, launchDate,price, title);

        return useCase.findAll(filter, new PageRequest(page, size))
                .map(BookDTOMapper::toDTO);
    }

    @GetMapping("/{id}")
    public BookResponseDTO findById(@PathVariable Long id) {
        return BookDTOMapper.toDTO(useCase.findById(id));
    }

    @PostMapping
    public BookResponseDTO create(@Valid @RequestBody BookCreateDTO dto) {
        Book book = BookDTOMapper.toDomain(dto);
        Book saved = useCase.create(book);
        return BookDTOMapper.toDTO(saved);
    }

    @PutMapping("/{id}")
    public BookResponseDTO update(@PathVariable Long id,
                                 @RequestBody BookUpdateDTO dto) {

        Book existing = useCase.findById(id);
        BookDTOMapper.updateDomain(existing, dto);
        Book updated = useCase.update(id, existing);

        return BookDTOMapper.toDTO(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        useCase.delete(id);
    }
}