package com.app.controllers;

import com.app.entities.Author;
import com.app.entities.Book;
import com.app.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
@ExtendWith(SpringExtension.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

   /* @InjectMocks
    private BookController bookController;*/

    @Autowired
    private ObjectMapper objectMapper;

    Book book1;
    Book book2;
    List<Book> bookList=new ArrayList<>();


    @BeforeEach
    void setUp() {

        Author author1=new Author();
        author1.setFirstName("Shaurya");
        author1.setLastName("Kapoor");
        book1=new Book();
        book1.setTitle("Art Of Not Overthinking");
        book1.setAuthor(author1);

        Author author2=new Author();
        author2.setFirstName("Sharad");
        author2.setLastName("Tandale");
        book2=new Book();
        book2.setTitle("Ravan");
        book2.setAuthor(author2);

        bookList.add(book1);
        bookList.add(book2);
    }

    @Test
    public void BookController_getAllBooks_ReturnListOfBooks() throws Exception {
         when(bookService.getAllBooks()).thenReturn(bookList);
         mockMvc.perform(get("/books"))
                 .andExpect(status().isFound())
                 .andExpect(jsonPath("$.size()").value(2))
                 .andExpect(jsonPath("$.[1].title").value("Ravan"));
    }

    @Test
    public void BookController_getBookById_ReturnBook() throws Exception{
        when(bookService.findBookById(2)).thenReturn(book2);
        mockMvc.perform(get("/books/2"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.title").value("Ravan"));
    }

    @Test
    public void BookController_addBook_ReturnMessage() throws Exception {
        when(bookService.addBook(any(Book.class))).thenReturn(book1);
        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book1)))
                .andExpect(status().isCreated()) // 201 Created
                .andExpect(content().string("Book added successfully"));
    }

    @Test
    void BookController_updateBook_ReturnMessage() throws Exception {
        when(bookService.updateBook(any(Book.class),eq(1))).thenReturn(book1);
        mockMvc.perform(MockMvcRequestBuilders.put("/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book1)))
                .andExpect(status().isOk()) // 200 OK
                .andExpect(content().string("Book updated successfully"));

    }

    @Test
    public void BookController_deleteBookById_ReturnMessage() throws Exception {
        when(bookService.deleteBookById(1)).thenReturn("Deleted successfully");
        mockMvc.perform(delete("/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted successfully"));
    }
}