package com.app.controllers;

import com.app.entities.Book;
import com.app.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;


    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> books=bookService.getAllBooks();
        if(books.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
         return ResponseEntity.status(HttpStatus.FOUND).body(books);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") int id)
    {
        Book book=bookService.findBookById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(book);
    }

    @PostMapping("/books")
    public ResponseEntity<String> addBook(@RequestBody Book book)
    {
           Book book1= bookService.addBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).body("Book added successfully");
    }
                        
    @PutMapping("/books/{id}")
    public ResponseEntity<String> updateBook(@RequestBody Book book,@PathVariable("id") int id)
    {
        Book book1=bookService.updateBook(book,id);
        return ResponseEntity.status(HttpStatus.OK).body("Book updated successfully");
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") int id)
    {
        String msg=bookService.deleteBookById(id);
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }
}
