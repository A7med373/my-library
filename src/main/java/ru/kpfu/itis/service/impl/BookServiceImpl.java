package ru.kpfu.itis.service.impl;

import ru.kpfu.itis.dao.BookDao;
import ru.kpfu.itis.model.Book;
import ru.kpfu.itis.service.BookService;
import ru.kpfu.itis.util.DbException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

public class BookServiceImpl implements BookService {

    private BookDao bookDao;

    private final String TITLE_REGEX = "[\\p{L}\\s-]+";

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public List<Book> getBooks(String genre) {
        try {
            return bookDao.getBooksByGenre(genre);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book getById(UUID uuid) {
        try {
            return bookDao.getById(uuid);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean save(Book book, HttpServletRequest req) {
        try {
            if (!book.title().matches(TITLE_REGEX)) {
                req.setAttribute("error", "Название должно состоять только из букв");
                return false;
            } else {
                bookDao.save(book);
                return true;
            }
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Book newBook, Book oldBook, HttpServletRequest req) {
        try {
            if (!newBook.title().equals(oldBook.title()) && !newBook.title().matches(TITLE_REGEX)) {
                req.setAttribute("error", "Название должно состоять только из букв");
                req.setAttribute("book", newBook);
                return false;
            } else {
                bookDao.update(newBook);
                return true;
            }
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Book book) {
        try {
            bookDao.delete(book);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }
}
