package ru.kpfu.itis.dto;

import java.util.UUID;

public record AccountUpdateDto(
        UUID uuid,
        String username,
        String name,
        String lastname,
        String email,
        String about,
        String avatar
) {
}
