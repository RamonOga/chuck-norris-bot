package ru.example.jpa.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.example.bot.config.HibernateConf;
import ru.example.jpa.mapping.ChatMapper;
import ru.example.model.dto.ChatDto;

@Repository
public class ChatRepository extends CrudRepository {
    private final Logger logger = LoggerFactory.getLogger(ChatRepository.class);

    public ChatRepository(HibernateConf hibernateConf) {
        super(hibernateConf);
    }

    public void add(ChatDto chatDto) {
        execute((session -> session.merge(ChatMapper.map(chatDto))), logger);
    }
}
