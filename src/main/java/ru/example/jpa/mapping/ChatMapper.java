package ru.example.jpa.mapping;

import ru.example.jpa.entities.ChatEntity;
import ru.example.model.dto.ChatDto;

public class ChatMapper {
    public static ChatDto map(ChatEntity entity) {
        return ChatDto.builder()
                .id(entity.getChatId())
                .type(entity.getMessageType())
                .build();
    }

    public static ChatEntity map(ChatDto dto) {
        ChatEntity entity = new ChatEntity();
        entity.setChatId(dto.getId());
        entity.setMessageType(dto.getType());
        return entity;
    }
}
