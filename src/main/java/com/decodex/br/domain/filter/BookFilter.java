package com.decodex.br.domain.filter;

import java.time.LocalDate;

public class BookFilter {
	
	    private String author;
	    private LocalDate launchDate;
	    private Double price;
	    private String title;


	    public BookFilter(String author, LocalDate launchDate,Double price, String title) {
	        this.author = author;
	        this.launchDate = launchDate;
	        this.price = price;
	        this.title = title;
	    }

	    public String getAuthor() { return author; }
	    public LocalDate getLaunchDate() { return launchDate; }
	    public Double getPrice() { return price; }
	    public String getTitle() { return title; }
	}