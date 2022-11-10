package com.hibernate.test.service;

import org.springframework.stereotype.Component;

import com.hibernate.test.entity.Book;


@Component
public interface BookService {
    public Book saveBook(Book book);
    public Book findByBookId(int bookId);
}