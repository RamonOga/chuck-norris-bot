package ru.example.jpa.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.example.jpa.config.HibernateConf;
import ru.example.jpa.entities.MessageEntity;
import ru.example.jpa.repositories.interfaces.IMessageRepository;

@Repository
public class MessageRepository extends CrudRepository implements IMessageRepository {

    private final static Logger logger = LoggerFactory.getLogger(MessageRepository.class);

    public MessageRepository(HibernateConf hibernateConf) {
        super(hibernateConf);
    }

    @Override
    public MessageEntity add(MessageEntity messageEntity) {
        return execute((session -> session.merge(messageEntity)), logger);
    }
}
