package com.decodex.br.adapters.out.persistence.adapter;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.decodex.br.adapters.out.persistence.entity.PersonEntity;
import com.decodex.br.adapters.out.persistence.mapper.PersonMapper;
import com.decodex.br.adapters.out.persistence.repository.PersonJpaRepository;
import com.decodex.br.domain.filter.PersonFilter;
import com.decodex.br.domain.model.Person;
import com.decodex.br.domain.pagination.PageRequest;
import com.decodex.br.domain.pagination.PageResult;
import com.decodex.br.domain.port.out.PersonRepositoryPort;

@Component
public class PersonRepositoryAdapter implements PersonRepositoryPort {

    private final PersonJpaRepository repository;
    private final PersonMapper mapper;

    public PersonRepositoryAdapter(PersonJpaRepository repository) {
        this.repository = repository;
        this.mapper = new PersonMapper();
    }

    @Override
    public Person save(Person person) {
        return mapper.toDomain(
            repository.save(mapper.toEntity(person))
        );
    }

    @Override
    public Optional<Person> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public PageResult<Person> findAll(PersonFilter filter, PageRequest request) {

        org.springframework.data.domain.Pageable pageable =
            org.springframework.data.domain.PageRequest.of(
                request.getPage(),
                request.getSize()
            );

        Specification<PersonEntity> spec = (root, query, cb) -> cb.conjunction();

        if (filter.getFirstName() != null) {
            spec = spec.and((root, query, cb) ->
                cb.like(cb.lower(root.get("firstName")),
                    "%" + filter.getFirstName().toLowerCase() + "%"));
        }

        if (filter.getLastName() != null) {
            spec = spec.and((root, query, cb) ->
                cb.like(cb.lower(root.get("lastName")),
                    "%" + filter.getLastName().toLowerCase() + "%"));
        }
        
        if (filter.getAddress() != null) {
            spec = spec.and((root, query, cb) ->
                cb.like(cb.lower(root.get("address")),
                    "%" + filter.getAddress().toLowerCase() + "%"));
        }

        if (filter.getGender() != null) {
            spec = spec.and((root, query, cb) ->
                cb.equal(root.get("gender"), filter.getGender()));
        }

        Page<PersonEntity> page = repository.findAll(spec, pageable);

        return new PageResult<>(
            page.getContent().stream().map(mapper::toDomain).toList(),
            page.getNumber(),
            page.getSize(),
            page.getTotalElements(),
            page.getTotalPages()
        );
    }
    
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}