package ru.example.model;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.example.model.dto.ChatDto;
import ru.example.model.dto.MessageDto;
import ru.example.model.dto.UpdateDto;
import ru.example.model.dto.UserDto;

@Component
public class UpdateDtoFactory {
    public UpdateDto getUpdateDto(Update telegramUpdate) {
        UserDto userDto = getUserDto(telegramUpdate.getMessage().getFrom());
        ChatDto chatDto = getChatDto(telegramUpdate.getMessage().getChat());
        Message telegramMessage = telegramUpdate.getMessage();
        MessageDto messageDto = MessageDto.builder()
                .id(telegramMessage.getMessageId())
                .from(userDto).date(telegramMessage.getDate())
                .chat(chatDto)
                .text(telegramMessage.getText())
                .build();

        return UpdateDto.builder()
                .message(messageDto)
                .id(telegramUpdate.getUpdateId())
                .build();
    }

    private UserDto getUserDto(User telegramUser) {
        return UserDto.builder()
                .id(telegramUser.getId())
                .first_name(telegramUser.getFirstName())
                .last_name(telegramUser.getLastName())
                .is_bot(telegramUser.getIsBot())
                .username(telegramUser.getUserName())
                .build();
    }

    private ChatDto getChatDto(Chat telegramChatModel) {
        return ChatDto.builder()
                .id(telegramChatModel.getId())
                .type(telegramChatModel.getType()).build();
    }
}
