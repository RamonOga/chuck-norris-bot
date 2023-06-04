package ru.example.api;

import org.springframework.stereotype.Service;
import ru.example.model.Answer;
import ru.example.model.dto.MessageDto;

@Service
public class TelegramBotUpdateHandlerImpl implements TelegramBotUpdateHandler {
    @Override
    public TelegramBotUpdateResponse send(TelegramBotUpdateRequest request) {
        Answer answer = getAnswer(request.getUpdate().getMessage());
        return TelegramBotUpdateResponse.builder().answer(answer).build();
    }

    private Answer getAnswer(MessageDto message) {
        String text = message.getText();
        Answer.AnswerBuilder answerBuilder = Answer.builder().chatId(message.getChat().getId());
        switch (text) {
            case "/start":
                return answerBuilder.text( "Hi, " + message.getFrom().getUsername() + ", nice to meet you!").build();
            case ".":
                return answerBuilder.text(",").build();
            case ",":
                return answerBuilder.text("").build();
        }
        return answerBuilder.build();
    }
}
