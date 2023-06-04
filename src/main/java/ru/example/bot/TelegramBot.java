package ru.example.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.example.config.BotConfig;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.example.model.UpdateDtoFactory;

@Component
@AllArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;
    private final UpdateDtoFactory updateDtoFactory;
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
        if(update.hasMessage() && update.getMessage().hasText()){
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            logger.info(String.format("Update received: %s", updateDtoFactory.getUpdateDto(update)));
            switch (messageText){
                case "/start":
                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    break;
                case ".":
                    sendMessage(chatId,",");
                    break;
                case ",":
                    sendMessage(chatId,"");
                    break;
            }
        }
    }

    private void startCommandReceived(Long chatId, String name) {
        String answer = "Hi, " + name + ", nice to meet you!";
        sendMessage(chatId, answer);
    }

    private void sendMessage(Long chatId, String textToSend) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(String.valueOf(chatId))
                .text(textToSend).build();
        try {
            execute(sendMessage);
            logger.info(String.format("Message sent: %s", sendMessage.getText()));
        } catch (TelegramApiException e) {
            logger.error("Shit happened!!!", e);
        }
    }
}