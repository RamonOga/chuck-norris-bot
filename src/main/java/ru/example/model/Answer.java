package ru.example.model;

import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Builder(toBuilder = true)
@Getter
public class Answer {
    private final Long chatId;
    private final String text;

    public boolean isEmpty() {
        return StringUtils.isBlank(text);
    }
}
