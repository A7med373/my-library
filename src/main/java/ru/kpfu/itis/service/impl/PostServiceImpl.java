package ru.kpfu.itis.service.impl;

import ru.kpfu.itis.dao.PostDao;
import ru.kpfu.itis.model.Post;
import ru.kpfu.itis.service.PostService;
import ru.kpfu.itis.util.DbException;

import java.util.List;
import java.util.UUID;

public class PostServiceImpl implements PostService {

    private PostDao postDao;

    public PostServiceImpl(PostDao postDao) {
        this.postDao = postDao;
    }

    @Override
    public void save(Post post) {
        try {
            postDao.save(post);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Post> getAll() {
        try {
            return postDao.getAll();
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Post> getByTitle(String title) {
        try {
            return postDao.getByTitle(title);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Post post) {
        try {
            postDao.delete(post);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Post post) {
        try {
            postDao.update(post);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Post getById(UUID uuid) {
        try {
            return postDao.getById(uuid);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Post> getByAuthor(UUID uuid) {
        try {
            return postDao.getByAuthor(uuid);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }
}
