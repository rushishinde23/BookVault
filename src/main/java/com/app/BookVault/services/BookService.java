package com.app.BookVault.services;

import com.app.BookVault.daos.BookRepository;
import com.app.BookVault.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks(){

        List<Book> books=bookRepository.findAll();
        return books;
    }

    public  Book addBook(Book book){

        Book book1=bookRepository.save(book);
        return book1;
    }

    public Book findBookById(int id) {
        Book book=bookRepository.findById(id).get();
        return book;
    }

    public Book updateBook(Book book, int id) {

        book.setId(id);
        Book book1=bookRepository.save(book);
        return book1;
    }

    public String deleteBookById(int id) {
         bookRepository.deleteById(id);
         String msg="Book deleted successfully";
        return msg;
    }

}
