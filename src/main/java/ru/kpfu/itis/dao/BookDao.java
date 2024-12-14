package ru.kpfu.itis.dao;

import ru.kpfu.itis.model.Book;
import ru.kpfu.itis.util.DbException;

import java.sql.ResultSet;
import java.util.List;
import java.util.UUID;

public interface BookDao {

    List<Book> getBooksByGenre(String genre) throws DbException;

    Book getById(UUID uuid) throws DbException;

    Book extract(ResultSet result) throws DbException;

    void save(Book book) throws DbException;

    void update(Book book) throws DbException;

    void delete(Book book) throws DbException;

}
