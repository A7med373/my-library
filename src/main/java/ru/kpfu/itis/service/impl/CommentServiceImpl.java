package ru.kpfu.itis.service.impl;

import ru.kpfu.itis.dao.CommentDao;
import ru.kpfu.itis.dto.CommentEditDto;
import ru.kpfu.itis.dto.CommentUpdateDto;
import ru.kpfu.itis.model.Account;
import ru.kpfu.itis.model.Comment;
import ru.kpfu.itis.service.CommentService;
import ru.kpfu.itis.util.DbException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CommentServiceImpl implements CommentService {

    private CommentDao commentDao;

    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public void save(Comment comment) {
        try {
            commentDao.save(comment);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Comment getById(UUID uuid) {
        try {
            return commentDao.getById(uuid);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CommentEditDto> getAllByPostId(UUID uuid, Account currentAccount) {
        List<CommentEditDto> commentEditDtos = new ArrayList<>();
        try {
            List<Comment> comments = commentDao.getAllByPostId(uuid);
            for (Comment comment : comments) {
                if (currentAccount != null && comment.author().uuid().equals(currentAccount.uuid())) {
                    commentEditDtos.add(new CommentEditDto(comment.uuid(), comment.author(), comment.post(),
                            comment.content(), comment.date(), true));
                } else {
                    commentEditDtos.add(new CommentEditDto(comment.uuid(), comment.author(), comment.post(),
                            comment.content(), comment.date(), false));
                }
            }
            return commentEditDtos;
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Comment> getAllByPostId(UUID uuid) {
        try {
            return commentDao.getAllByPostId(uuid);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Comment comment) {
        try {
            commentDao.delete(comment);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(CommentUpdateDto updateComment) {
        try {
            Comment comment = new Comment(updateComment.uuid(), null, null, updateComment.content(),
                    updateComment.date());
            commentDao.update(comment);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

}
