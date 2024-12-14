package ru.kpfu.itis.service;

import ru.kpfu.itis.model.Post;

import java.util.List;
import java.util.UUID;

public interface PostService {
    void save(Post post);

    List<Post> getAll();

    List<Post> getByTitle(String title);

    void delete(Post post);

    void update(Post post);

    Post getById(UUID uuid);

    List<Post> getByAuthor(UUID uuid);
}
