package ru.example.jpa.repositories.interfaces;


import ru.example.jpa.entities.MessageEntity;

public interface IMessageRepository {
    MessageEntity add(MessageEntity messageEntity);
}
