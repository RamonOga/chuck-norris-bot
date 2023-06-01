package ru.example.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder(toBuilder = true)
@Getter
@ToString
public class UserDto {
    private final Long id;
    private final String first_name;
    private final Boolean is_bot;
    private final String last_name;
    private final String username;
}
