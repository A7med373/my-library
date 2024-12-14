package ru.kpfu.itis.dao;

import ru.kpfu.itis.model.Post;
import ru.kpfu.itis.util.DbException;

import java.sql.ResultSet;
import java.util.List;
import java.util.UUID;

public interface PostDao {

    void save(Post post) throws DbException;

    List<Post> getAll() throws DbException;

    List<Post> getByTitle(String title) throws DbException;

    Post getById(UUID uuid) throws DbException;

    void delete(Post post) throws DbException;

    void update(Post entity) throws DbException;

    List<Post> getByAuthor(UUID uuid) throws DbException;

    Post extract(ResultSet result) throws DbException;
}
