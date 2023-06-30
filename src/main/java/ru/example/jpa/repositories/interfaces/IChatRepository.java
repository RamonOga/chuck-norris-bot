package ru.example.jpa.repositories.interfaces;

import ru.example.jpa.entities.ChatEntity;

public interface IChatRepository {
    ChatEntity add(ChatEntity chatEntity);
    ChatEntity findById(long userId);
}
