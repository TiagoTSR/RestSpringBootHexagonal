package com.decodex.br.domain.model;

import java.time.LocalDate;
import java.util.Objects;

public class Book {

    private Long id;
    private String author;
    private LocalDate launchDate;
    private Double price;
    private String title;

    public Book(Long id, String author, LocalDate launchDate, Double price, String title) {
        this.id = id;
        this.author = validarTexto(author, "Author");
        this.launchDate = Objects.requireNonNull(launchDate, "Launch date não pode ser nula");
        this.price = validarPreco(price);
        this.title = validarTexto(title, "Title");
    }

    private String validarTexto(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(campo + " não pode ser vazio");
        }
        return valor;
    }

    private Double validarPreco(Double price) {
        if (price == null || price <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero");
        }
        return price;
    }

    public void alterarPreco(Double novoPreco) {
        this.price = validarPreco(novoPreco);
    }
    
    public void alterarNome(String author,String title) {
    	this.author = validarTexto(author, "Nome");
    	this.title = validarTexto(title,"Titulo");
    }
    
    public void alterarDataLancamento(LocalDate novaData) {
        this.launchDate = Objects.requireNonNull(novaData, "Data de lançamento não pode ser nula");
    }

    public Long getId() { return id; }
    public String getAuthor() { return author; }
    public LocalDate getLaunchDate() { return launchDate; }
    public Double getPrice() { return price; }
    public String getTitle() { return title; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}