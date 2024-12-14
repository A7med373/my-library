package ru.kpfu.itis.dto;

import java.util.Date;

public record AccountRegistrationDto(
        String username,
        String name,
        String lastname,
        Date birthday,
        String email,
        String password
) {
}
