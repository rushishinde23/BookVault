package com.app.daos;

import com.app.entities.Author;
import com.app.entities.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    Book book;

    @BeforeEach
    void setUp() {
      Author author=new Author();
      author.setFirstName("swami");
      author.setLastName("vivekanand");

      book=new Book();
      book.setTitle("rajyog");
      book.setAuthor(author);
        /*Author author=Author.builder().firstName("swami").lastName("vivekananda").build();
        Book book = Book.builder().title("Rajyog").author(author).build();*/

      bookRepository.save(book);
    }

    @Test
    public void BookRepository_FindById_ReturnNotNull() {
        Book foundBook=bookRepository.findById(book.getId()).get();
         assertThat(foundBook).isNotNull();
        //Book foundBook1=null;
        //assertThat(foundBook1).isNull();
    }

    @Test
    public void BookRepository_Save_ReturnSavedBook(){
        Book savedBook=bookRepository.save(book);
        assertThat(savedBook.getTitle()).isEqualTo("rajyog");
    }

    @Test
    public void BookRepository_FindAll_ReturnAllBooks(){
        List<Book> bookList=bookRepository.findAll();
        assertThat(bookList.size()).isEqualTo(1);
    }

    @Test
    public void BookRepository_UpdateBook_ReturnUpdated(){
        Book book1=bookRepository.save(book);
        book1.setTitle("Karmayog");
        Book updatedBook=bookRepository.save(book1);
        assertThat(updatedBook.getTitle()).isEqualTo("Karmayog");
    }

    @Test
    public void BookRepository_DeleteBook_ReturnNull(){
         bookRepository.deleteById(book.getId());
         Optional<Book> book1=bookRepository.findById(book.getId());
         assertThat(book1).isEmpty();
    }

}