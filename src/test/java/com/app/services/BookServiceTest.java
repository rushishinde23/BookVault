package com.app.services;

import com.app.daos.BookRepository;
import com.app.entities.Author;
import com.app.entities.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private Book book;
    @BeforeEach
    void setUp() {
        Author author=new Author(1,"Shaurya","Kapoor",null);
         book=new Book(1,"Art of not overthinking",author);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void BookService_getAllBooks_ReturnAllBooks() {
       when(bookRepository.findAll()).thenReturn(Arrays.asList(book));
        List<Book> bookList=bookService.getAllBooks();
        assertEquals(1,bookList.size());
    }

    @Test
    void BookService_addBook_ReturnBook() {
        when(bookRepository.save(book)).thenReturn(book);
        Book book1=bookService.addBook(book);
        assertEquals(1,book1.getId());
    }

    @Test
    public void BookService_findBookById_ReturnBook() {
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        Book book1=bookService.findBookById(1);
        assertNotNull(book1);
        assertEquals("Art of not overthinking",book1.getTitle());
    }

    @Test
    public void BookService_updateBook_ReturnBook() {
        when(bookRepository.save(book)).thenReturn(book);
        Book book1=bookService.addBook(book);
        assertEquals("Shaurya",book1.getAuthor().getFirstName());
    }

    @Test
    public void BookService_deleteBookById_ReturnNull() {
        doNothing().when(bookRepository).deleteById(1);
        String msg=bookService.deleteBookById(1);
        assertEquals("Book deleted successfully",msg);
    }
}