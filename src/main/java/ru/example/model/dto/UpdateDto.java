package ru.example.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder(toBuilder = true)
@Getter
@ToString
public class UpdateDto {
    private final Integer id;
    private final MessageDto message;
}
