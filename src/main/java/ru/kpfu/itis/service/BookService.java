package ru.kpfu.itis.service;


import ru.kpfu.itis.model.Book;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

public interface BookService {

    List<Book> getBooks(String sort);

    Book getById(UUID uuid);

    boolean save(Book book, HttpServletRequest req);

    boolean update(Book newBook, Book oldBook, HttpServletRequest req);

    void delete(Book book);
}
