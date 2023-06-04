package ru.example.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.example.api.TelegramBotUpdateHandler;
import ru.example.api.TelegramBotUpdateRequest;
import ru.example.config.BotConfig;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.example.model.Answer;
import ru.example.model.UpdateDtoFactory;
import ru.example.model.dto.UpdateDto;

@Component
@AllArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;
    private final UpdateDtoFactory updateDtoFactory;
    private final TelegramBotUpdateHandler handler;
    private final Logger logger = LoggerFactory.getLogger(TelegramBot.class);


    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            UpdateDto updateDto = updateDtoFactory.getUpdateDto(update);
            TelegramBotUpdateRequest request = TelegramBotUpdateRequest.builder()
                    .update(updateDto)
                    .build();
            Answer answer = handler.send(request)
                    .getAnswer();
            if (!answer.isEmpty()) {
                sendMessage(answer);
            }
        }
    }

    private void sendMessage(Answer answer) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(String.valueOf(answer.getChatId()))
                .text(answer.getText()).build();
        try {
            execute(sendMessage);
            logger.info(String.format("Message sent: %s", sendMessage.getText()));
        } catch (TelegramApiException e) {
            logger.error("Shit happened!!!", e);
        }
    }
}