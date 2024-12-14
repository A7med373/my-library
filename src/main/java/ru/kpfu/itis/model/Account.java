package ru.kpfu.itis.model;

import java.util.Date;
import java.util.UUID;

public record Account(
        UUID uuid,
        String username,
        String name,
        String lastname,
        Date birthday,
        String email,
        String password,
        String avatar,
        String about,
        Role role
) {
}