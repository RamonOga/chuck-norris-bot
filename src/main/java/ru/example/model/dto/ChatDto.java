package ru.example.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder(toBuilder = true)
@Getter
@ToString
public class ChatDto {
    private final Long id;
    private final String type;

}
