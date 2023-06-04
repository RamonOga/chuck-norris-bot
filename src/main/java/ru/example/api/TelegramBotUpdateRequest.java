package ru.example.api;

import lombok.Builder;
import lombok.Getter;
import ru.example.model.dto.UpdateDto;

@Builder(toBuilder = true)
@Getter
public class TelegramBotUpdateRequest {
    private final UpdateDto update;
}
