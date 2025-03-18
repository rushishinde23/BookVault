package com.app.BookVault.daos;

import com.app.BookVault.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    Optional<Book> findById(int id);
}
