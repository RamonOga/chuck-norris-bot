package ru.example.api;

import lombok.Builder;
import lombok.Getter;
import ru.example.model.Answer;

@Builder(toBuilder = true)
@Getter
public class TelegramBotUpdateResponse {
    private final Answer answer;
    public boolean isEmpty() {
        return answer.isEmpty();
    }
}
