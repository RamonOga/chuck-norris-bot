package ru.example.jpa.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.example.bot.config.HibernateConf;
import ru.example.jpa.entities.MessageEntity;
import ru.example.jpa.mapping.ChatMapper;
import ru.example.jpa.mapping.UserMapper;
import ru.example.model.dto.MessageDto;

@Repository
public class MessageRepository extends CrudRepository {

    private final static Logger logger = LoggerFactory.getLogger(MessageRepository.class);

    public MessageRepository(HibernateConf hibernateConf) {
        super(hibernateConf);
    }

    public void add(MessageDto messageDto) {
        MessageEntity entity = new MessageEntity();
        entity.setMessageId(messageDto.getId());
        entity.setUserId(UserMapper.map(messageDto.getFrom()));
        entity.setDate(messageDto.getDate());
        entity.setChatId(ChatMapper.map(messageDto.getChat()));
        entity.setText(messageDto.getText());
        entity.setCaption(messageDto.getCaption());
        entity.setMessageType(messageDto.getMessageType().name());
        execute((session -> session.merge(entity)), logger);
    }
}
