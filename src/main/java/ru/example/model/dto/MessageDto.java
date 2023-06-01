package ru.example.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder(toBuilder = true)
@Getter
@ToString
public class MessageDto {
    private final Integer id;
    private final UserDto from;
    private final Integer date;
    private final ChatDto chat;
    private final String text;

}
