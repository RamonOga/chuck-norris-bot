package ru.example.api;

public interface TelegramBotUpdateHandler {
    TelegramBotUpdateResponse send(TelegramBotUpdateRequest request);

}
