package ru.kpfu.itis.dto;

import java.util.Date;
import java.util.UUID;

public record CommentUpdateDto(
        UUID uuid,
        String content,
        Date date) {
}
