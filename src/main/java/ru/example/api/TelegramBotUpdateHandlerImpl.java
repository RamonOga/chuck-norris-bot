package ru.example.api;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import ru.example.model.Answer;
import ru.example.model.dto.MessageDto;
import ru.example.model.dto.MessageType;

import java.io.File;
import java.io.IOException;

@Service
public class TelegramBotUpdateHandlerImpl implements TelegramBotUpdateHandler {
    @Override
    public TelegramBotUpdateResponse send(TelegramBotUpdateRequest request) {
        MessageDto messageDto = request.getUpdate().getMessage();
        return TelegramBotUpdateResponse.builder().answer(fillAnswer(Answer.builder(), messageDto)).build();
    }

    private Answer fillAnswer(Answer.AnswerBuilder builder, MessageDto messageDto) {
        if (messageDto.getMessageType() == MessageType.VIDEO) {
            return builder.text("Хуя, это видео")
                    .chatId(messageDto.getChat().getId())
                    .build();
        }
        if (messageDto.getMessageType() == MessageType.PHOTO) {
            return builder.text("Хуя, это картинка")
                    .chatId(messageDto.getChat().getId())
                    .build();
        }
        return getTextAnswer(messageDto);
    }

    private Answer getTextAnswer(MessageDto message) {
        String text = message.getText();
        Answer.AnswerBuilder answerBuilder = Answer.builder().chatId(message.getChat().getId());
        switch (text) {
            case "/start":
                return answerBuilder.text("Hi, " + message.getFrom().getUsername() + ", nice to meet you!").build();
            case ".":
                return answerBuilder.text(",").build();
            case ",":
                return answerBuilder.text("").build();
        }
        return answerBuilder.build();
    }

//   if (update.hasMessage() && update.getMessage().hasPhoto())
//    {
//
//        long chat_id = update.getMessage().getChatId();
//        List<PhotoSize> photos = update.getMessage().getPhoto();
//
//        String f_id = photos.stream()
//                .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
//                .findFirst()
//                .orElse(null).getFileId();
//
//
//
//        GetFile getFileRequest = new GetFile();
//        getFileRequest.setFileId(update.getMessage().getPhoto().get(0).getFileId());
//        org.telegram.telegrambots.api.objects.File file = getFile(getFileRequest);
//
//        File f_path = new File("https://api.telegram.org/file/bot"+getBotToken()+"/"+file.getFilePath());
//
//        String caption = "file_id: " + f_id + "\nfile_path: " + f_path;
//
//        SendPhoto msg = new SendPhoto()
//                .setChatId(chat_id)
//                .setPhoto(f_id)
//                .setCaption(caption);
//        try {
//            sendPhoto(msg);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
}
