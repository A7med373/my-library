package ru.kpfu.itis.dao.impl;

import ru.kpfu.itis.dao.AccountDao;
import ru.kpfu.itis.dao.PostDao;
import ru.kpfu.itis.model.Account;
import ru.kpfu.itis.model.Post;
import ru.kpfu.itis.util.ConnectionProvider;
import ru.kpfu.itis.util.DbException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class PostDaoImpl implements PostDao {

    private ConnectionProvider connectionProvider;
    private AccountDao accountDao;

    //language=sql
//    final String SQL_SAVE = "insert into post(author_uuid, date, title, content, image) " +
//            "values(?, ?, ?, ?, ?)";

    // Обновлённый SQL-запрос с включением поля uuid
    final String SQL_SAVE = "INSERT INTO post(uuid, author_uuid, date, title, content, image) " +
            "VALUES(?, ?, ?, ?, ?, ?)";
    //language=sql
    final String SQL_GET_ALL = "select * from post";

    //language=sql
    final String SQL_GET_BY_AUTHOR = "select * from post where author_uuid = cast(? as uuid)";

    //language=sql
    final String SQL_GET_BY_UUID = "select * from post where uuid = cast(? as uuid)";

    //language=sql
    final String SQL_GET_BY_TITLE = "select * from post where lower(title) like ? order by title";

    //language=sql
    final String SQL_UPDATE_WITHOUT_IMAGE = "update post set title = ?, content = ?, " +
            "date = cast(? as date) where uuid = cast(? as uuid)";

    //language=sql
    final String SQL_UPDATE = "update post set title = ?, content = ?, image = ?, " +
            "date = cast(? as date) where uuid = cast(? as uuid)";

    //language=sql
    final String SQL_DELETE_BY_ID = "delete from post where uuid = cast(? as uuid)";

    public PostDaoImpl(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
        this.accountDao = new AccountDaoImpl(this.connectionProvider);
    }

    @Override
    public void save(Post post) throws DbException {
        try {
            PreparedStatement preparedStatement = this.connectionProvider.getConnection()
                    .prepareStatement(SQL_SAVE);

            preparedStatement.setObject(1, post.uuid()); // UUID поста
            preparedStatement.setObject(2, post.author().uuid()); // UUID автора
            preparedStatement.setTimestamp(3, new java.sql.Timestamp(post.date().getTime())); // Дата
            preparedStatement.setString(4, post.title()); // Заголовок
            preparedStatement.setString(5, post.content()); // Контент
            preparedStatement.setString(6, post.image()); // Изображение

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DbException("Can't save post", e);
        }
    }

    @Override
    public List<Post> getAll() throws DbException {
        List<Post> posts = new ArrayList<>();
        try {
            PreparedStatement prepStatement = this.connectionProvider.getConnection().prepareStatement(SQL_GET_ALL);
            ResultSet resultPrepSet = prepStatement.executeQuery();
            while (resultPrepSet.next()) {
                posts.add(extract(resultPrepSet));
            }
        } catch (SQLException e) {
            throw new DbException("Can't get post from db.", e);
        }
        return posts;
    }

    @Override
    public List<Post> getByTitle(String title) throws DbException {
        List<Post> posts = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connectionProvider
                    .getConnection()
                    .prepareStatement(SQL_GET_BY_TITLE);
            preparedStatement.setString(1, "%" + title.toLowerCase() + "%");
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                posts.add(extract(result));
            }
        } catch (SQLException e) {
            throw new DbException("Can't get post from db.", e);
        }
        return posts;
    }

    @Override
    public void delete(Post post) throws DbException {
        try {
            PreparedStatement preparedStatement = this.connectionProvider.getConnection()
                    .prepareStatement(SQL_DELETE_BY_ID);
            preparedStatement.setObject(1, post.uuid());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Can't delete post", e);
        }
    }

    @Override
    public void update(Post post) throws DbException {
        try {
            if (post.image() != null) {
                PreparedStatement preparedStatement = this.connectionProvider.getConnection()
                        .prepareStatement(SQL_UPDATE);
                String date = String.format("%d-%02d-%02d",
                        post.date().getYear() + 1900,
                        post.date().getMonth() + 1,
                        post.date().getDate());
                preparedStatement.setString(1, post.title());
                preparedStatement.setString(2, post.content());
                preparedStatement.setString(3, post.image());
                preparedStatement.setString(4, date);
                preparedStatement.setObject(5, post.uuid());
                preparedStatement.executeUpdate();
            } else {
                PreparedStatement preparedStatement = this.connectionProvider.getConnection()
                        .prepareStatement(SQL_UPDATE_WITHOUT_IMAGE);
                String date = String.format("%d-%02d-%02d",
                        post.date().getYear() + 1900,
                        post.date().getMonth() + 1,
                        post.date().getDate());
                preparedStatement.setString(1, post.title());
                preparedStatement.setString(2, post.content());
                preparedStatement.setString(3, date);
                preparedStatement.setObject(4, post.uuid());
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            throw new DbException("Can't update post", e);
        }
    }

    @Override
    public Post getById(UUID uuid) throws DbException {
        try {
            PreparedStatement preparedStatement = this.connectionProvider
                    .getConnection()
                    .prepareStatement(SQL_GET_BY_UUID);
            preparedStatement.setString(1, uuid.toString());
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                return extract(result);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DbException("Can't get post from db.", e);
        }
    }

    @Override
    public List<Post> getByAuthor(UUID uuid) throws DbException {
        List<Post> posts = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connectionProvider
                    .getConnection()
                    .prepareStatement(SQL_GET_BY_AUTHOR);
            preparedStatement.setString(1, uuid.toString());
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                posts.add(extract(result));
            }
        } catch (SQLException e) {
            throw new DbException("Can't get post from db.", e);
        }
        return posts;
    }

    @Override
    public Post extract(ResultSet result) throws DbException {
        try {
            UUID uuid = (UUID) result.getObject("uuid");
            UUID authorUuid = (UUID) result.getObject("author_uuid");
            Account author = accountDao.getById(authorUuid);
            String title = result.getString("title");
            String content = result.getString("content");
            String image = result.getString("image");
            Date date = result.getDate("date");

            return new Post(uuid, author, title, content, image, date);
        } catch (SQLException e) {
            throw new DbException("Can't get post from db.", e);
        }
    }
}
