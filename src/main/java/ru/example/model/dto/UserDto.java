package ru.example.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder(toBuilder = true)
@Getter
@ToString
public class UserDto {
    private final Long id;
    private final String firstName;
    private final Boolean isBot;
    private final String lastName;
    private final String username;
}
