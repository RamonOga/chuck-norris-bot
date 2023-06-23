package ru.example.model;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.*;
import ru.example.model.dto.*;

@Component
public class UpdateDtoFactory {

    public UpdateDto getUpdateDto(Update telegramUpdate) {
        UserDto userDto = getUserDto(telegramUpdate.getMessage().getFrom());
        ChatDto chatDto = getChatDto(telegramUpdate.getMessage().getChat());
        Message telegramMessage = telegramUpdate.getMessage();
        MessageDto messageDto = fillMessageContent(telegramMessage, MessageDto.builder())
                .id(telegramMessage.getMessageId())
                .from(userDto).date(telegramMessage.getDate())
                .chat(chatDto)
                .caption(telegramMessage.getCaption())
                .build();


        return UpdateDto.builder()
                .message(messageDto)
                .id(telegramUpdate.getUpdateId())
                .build();
    }

    private UserDto getUserDto(User telegramUser) {
        return UserDto.builder()
                .id(telegramUser.getId())
                .firstName(telegramUser.getFirstName())
                .lastName(telegramUser.getLastName())
                .isBot(telegramUser.getIsBot())
                .username(telegramUser.getUserName())
                .build();
    }

    private ChatDto getChatDto(Chat telegramChatModel) {
        return ChatDto.builder()
                .id(telegramChatModel.getId())
                .type(telegramChatModel.getType()).build();
    }

    private MessageDto.MessageDtoBuilder fillMessageContent(Message telegramMessage, MessageDto.MessageDtoBuilder builder) {
        if (telegramMessage.getPhoto() != null) {
            return builder.photo(getPhoto(telegramMessage.getPhoto().get(3)))
                    .messageType(MessageType.PHOTO);
        }
        if (telegramMessage.getVideo() != null) {
            return builder.video(getVideo(telegramMessage.getVideo()))
                    .messageType(MessageType.VIDEO);
        }
        return builder.text(telegramMessage.getText())
                .messageType(MessageType.TEXT);
    }

    private PhotoDto getPhoto(PhotoSize photoSize) {
        return PhotoDto.builder()
                .id(photoSize.getFileId())
                .fileUniqueId(photoSize.getFileUniqueId())
                .width(photoSize.getWidth())
                .height(photoSize.getHeight())
                .fileSize(photoSize.getFileSize())
                .filePath(photoSize.getFilePath())
                .build();
    }

    private VideoDto getVideo(Video video) {
        return VideoDto.builder()
                .id(video.getFileId())
                .fileUniqueId(video.getFileUniqueId())
                .width(video.getWidth())
                .height(video.getHeight())
                .duration(video.getDuration())
                .thumb(getPhoto(video.getThumb()))
                .mimeType(video.getMimeType())
                .fileSize(video.getFileSize())
                .fileName(video.getFileName())
                .build();
    }
}
