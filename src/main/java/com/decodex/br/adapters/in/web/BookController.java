package com.decodex.br.adapters.in.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;

import com.decodex.br.application.dto.book.BookCreateDTO;
import com.decodex.br.application.dto.book.BookResponseDTO;
import com.decodex.br.application.dto.book.BookUpdateDTO;
import com.decodex.br.application.mapper.BookDTOMapper;
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
    public List<BookResponseDTO> findAll() {
        return useCase.findAll()
                .stream()
                .map(BookDTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BookResponseDTO findById(@PathVariable Long id) {
        return BookDTOMapper.toDTO(useCase.findById(id));
    }

    @PostMapping
    public BookResponseDTO create(@RequestBody BookCreateDTO dto) {
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