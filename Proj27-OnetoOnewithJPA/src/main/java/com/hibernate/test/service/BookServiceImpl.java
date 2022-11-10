package com.hibernate.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hibernate.test.entity.Book;
import com.hibernate.test.entity.Story;
import com.hibernate.test.repo.BookRepository;




@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    public Book saveBook(Book book) {
        Story story = book.getStory();
        story.setBook(book);
        book = bookRepository.save(book);
        return book;
    }
    public Book findByBookId(int bookId) {
        Book book = bookRepository.findByBookId(bookId);
        return book;
    }
}
