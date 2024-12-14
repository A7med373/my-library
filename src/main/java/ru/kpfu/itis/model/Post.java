package ru.kpfu.itis.model;

import java.util.Date;
import java.util.UUID;

public record Post(
        UUID uuid,
        Account author,
        String title,
        String content,
        String image,
        Date date
) {
}
