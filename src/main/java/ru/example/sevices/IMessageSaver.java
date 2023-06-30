package ru.example.sevices;

import ru.example.model.dto.MessageDto;

public interface IMessageSaver {
    void save(MessageDto messageDto);
}
