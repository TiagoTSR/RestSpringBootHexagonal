package com.decodex.br.domain.pagination;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PageResult<T> {

    private List<T> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;

    public PageResult(List<T> content, int page, int size, long totalElements, int totalPages) {
        this.content = content;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

	public List<T> getContent() {
		return content;
	}

	public int getPage() {
		return page;
	}

	public int getSize() {
		return size;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public int getTotalPages() {
		return totalPages;
	}
	
	public <R> PageResult<R> map(Function<? super T, ? extends R> mapper) {
        List<R> mappedContent = this.content.stream()
                .map(mapper)
                .collect(Collectors.toList());
        return new PageResult<>(mappedContent, this.page, this.size, this.totalElements, this.totalPages);
    }
}