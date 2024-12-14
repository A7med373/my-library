package ru.kpfu.itis.dto;

import ru.kpfu.itis.model.Account;
import ru.kpfu.itis.model.Post;

import java.util.Date;
import java.util.UUID;

public record CommentEditDto(
         UUID uuid,
         Account author,
         Post post,
         String content,
         Date date,
         boolean isEdit
) {
}
