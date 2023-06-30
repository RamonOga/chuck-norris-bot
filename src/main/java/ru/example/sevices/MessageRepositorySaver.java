package ru.example.sevices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.example.jpa.entities.ChatEntity;
import ru.example.jpa.entities.MessageEntity;
import ru.example.jpa.entities.UserEntity;
import ru.example.jpa.mapping.ChatMapper;
import ru.example.jpa.mapping.UserMapper;
import ru.example.jpa.repositories.interfaces.IChatRepository;
import ru.example.jpa.repositories.interfaces.IMessageRepository;
import ru.example.jpa.repositories.interfaces.IUserRepository;
import ru.example.model.dto.ChatDto;
import ru.example.model.dto.MessageDto;
import ru.example.model.dto.UserDto;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MessageRepositorySaver implements IMessageSaver {
    private final IMessageRepository messageRepository;
    private final IUserRepository userRepository;
    private final IChatRepository chatRepository;

    @Override
    public void save(MessageDto messageDto) {
        UserDto userDto = messageDto.getFrom();
        ChatDto chatDto = messageDto.getChat();
        UserEntity userEntity = Optional.ofNullable(userRepository.findById(userDto.getId()))
                .orElseGet(() -> userRepository.add(UserMapper.map(userDto)));

        ChatEntity chatEntity = Optional.ofNullable(chatRepository.findById(chatDto.getId()))
                .orElseGet(() -> chatRepository.add(ChatMapper.map(chatDto)));

        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setMessageId(messageDto.getId());
        messageEntity.setUserId(userEntity);
        messageEntity.setDate(messageDto.getDate());
        messageEntity.setChatId(chatEntity);
        messageEntity.setText(messageDto.getText());
        messageEntity.setCaption(messageDto.getCaption());
        messageEntity.setMessageType(messageDto.getMessageType().name());

        messageRepository.add(messageEntity);
    }
}
