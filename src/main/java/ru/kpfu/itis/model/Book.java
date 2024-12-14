package ru.kpfu.itis.model;

import java.util.UUID;

public record Book(
        UUID uuid,
        String title,
        String author,
        String genre,
        boolean isRead,
        String review
) {
}
