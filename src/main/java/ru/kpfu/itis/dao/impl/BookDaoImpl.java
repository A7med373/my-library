package ru.kpfu.itis.dao.impl;

import ru.kpfu.itis.dao.BookDao;
import ru.kpfu.itis.model.Book;
import ru.kpfu.itis.util.ConnectionProvider;
import ru.kpfu.itis.util.DbException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookDaoImpl implements BookDao {

    private ConnectionProvider connectionProvider;

    //language=sql
    final String SQL_GET_BY_GENRE = "select * from book where genre = ?";

    //language=sql
    final String SQL_GET_BY_UUID = "select * from book where uuid = cast(? as uuid)";

    //language=sql
    final String SQL_SAVE = "insert into book values (cast(? as uuid), ?, ?, ?, ?, ?)";

    //language=sql
    final String SQL_UPDATE = "update book set title = ?, author = ?, genre = ?, is_read = ?, review = ? " +
            "where uuid = cast(? as uuid)";

    //language=sql
    final String SQL_DELETE_BY_ID = "delete from book where uuid = cast(? as uuid)";

    public BookDaoImpl(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public List<Book> getBooksByGenre(String genre) throws DbException {
        List<Book> books = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connectionProvider
                    .getConnection()
                    .prepareStatement(SQL_GET_BY_GENRE);
            preparedStatement.setString(1, genre);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                books.add(extract(resultSet));
            }
            return books;
        } catch (SQLException e) {
            throw new DbException("Can't get books from db.", e);
        }
    }

    @Override
    public Book getById(UUID uuid) throws DbException {
        try {
            PreparedStatement preparedStatement = this.connectionProvider
                    .getConnection()
                    .prepareStatement(SQL_GET_BY_UUID);
            preparedStatement.setString(1, String.valueOf(uuid));
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                return extract(result);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DbException("Can't get book from db.", e);
        }
    }

    @Override
    public Book extract(ResultSet result) throws DbException {
        try {
            return new Book(
                    (UUID) result.getObject("uuid"),
                    result.getString("title"),
                    result.getString("author"),
                    result.getString("genre"),
                    result.getBoolean("is_read"),
                    result.getString("review")
            );
        } catch (SQLException e) {
            throw new DbException("Can't extract book from result set.", e);
        }
    }

    @Override
    public void save(Book book) throws DbException {
        try {
            PreparedStatement preparedStatement = this.connectionProvider.getConnection()
                    .prepareStatement(SQL_SAVE);
            preparedStatement.setObject(1, book.uuid());
            preparedStatement.setString(2, book.title());
            preparedStatement.setString(3, book.author());
            preparedStatement.setString(4, book.genre());
            preparedStatement.setBoolean(5, book.isRead());
            preparedStatement.setString(6, book.review());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Can't save book", e);
        }
    }

    @Override
    public void update(Book book) throws DbException {
        try {
            PreparedStatement preparedStatement = this.connectionProvider.getConnection()
                    .prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, book.title());
            preparedStatement.setString(2, book.author());
            preparedStatement.setString(3, book.genre());
            preparedStatement.setBoolean(4, book.isRead());
            preparedStatement.setString(5, book.review());
            preparedStatement.setObject(6, book.uuid());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Can't update book", e);
        }
    }

    @Override
    public void delete(Book book) throws DbException {
        try {
            PreparedStatement preparedStatement = this.connectionProvider.getConnection()
                    .prepareStatement(SQL_DELETE_BY_ID);
            preparedStatement.setObject(1, book.uuid());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Can't delete book", e);
        }
    }
}
