package ru.kpfu.itis.model;

import java.util.Date;
import java.util.UUID;

public record Comment(
        UUID uuid,
        Account author,
        Post post,
        String content,
        Date date
) {
}
