package ru.example.bot;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.example.api.TelegramBotUpdateHandler;
import ru.example.api.TelegramBotUpdateRequest;
import ru.example.bot.config.BotConfig;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.example.model.Answer;
import ru.example.model.UpdateDtoFactory;
import ru.example.model.dto.MessageDto;
import ru.example.model.dto.MessageType;
import ru.example.model.dto.UpdateDto;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;
    private final UpdateDtoFactory updateDtoFactory;
    private final TelegramBotUpdateHandler handler;
    private final Logger logger = LoggerFactory.getLogger(TelegramBot.class);

    public TelegramBot(BotConfig botConfig, UpdateDtoFactory updateDtoFactory, TelegramBotUpdateHandler handler) {
        super(new DefaultBotOptions(), botConfig.getToken());
        this.botConfig = botConfig;
        this.updateDtoFactory = updateDtoFactory;
        this.handler = handler;
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText() || message.hasPhoto() || message.hasVideo()) {
                UpdateDto updateDto = prepareUpdateDto(updateDtoFactory.getUpdateDto(update));
                TelegramBotUpdateRequest request = TelegramBotUpdateRequest.builder()
                        .update(updateDto)
                        .build();
                Answer answer = handler.send(request)
                        .getAnswer();
                if (!answer.isEmpty()) {
                    sendTextMessage(answer);
                }
            }
        }
    }

    private UpdateDto prepareUpdateDto(UpdateDto updateDto) {
        MessageDto message = updateDto.getMessage();
        if (message.getMessageType() == MessageType.PHOTO) {
            message.getPhoto().setBytes(downloadBytes(message.getPhoto().getId()));
        }
        if (message.getMessageType() == MessageType.VIDEO) {
            message.getVideo().setBytes(downloadBytes(message.getVideo().getId()));
        }
        return updateDto;
    }

    @SneakyThrows
    private byte[] downloadBytes(String fileId ) {
        GetFile getFile = new GetFile(fileId);
        execute(getFile);
        String filePath = execute(getFile).getFilePath();
        java.io.File file = downloadFile(filePath);
        return FileUtils.readFileToByteArray(file);
    }

    private void sendTextMessage(Answer answer) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(String.valueOf(answer.getChatId()))
                .text(answer.getText()).build();
        try {
            Message execute = execute(sendMessage);
            logger.info(String.format("Message sent: %s", sendMessage.getText()));
        } catch (TelegramApiException e) {
            logger.error("Shit happened!!!", e);
        }
    }
}