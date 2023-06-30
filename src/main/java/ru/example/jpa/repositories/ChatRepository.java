package ru.example.jpa.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.example.jpa.config.HibernateConf;
import ru.example.jpa.entities.ChatEntity;
import ru.example.jpa.repositories.interfaces.IChatRepository;

@Repository
public class ChatRepository extends CrudRepository implements IChatRepository {
    private final Logger logger = LoggerFactory.getLogger(ChatRepository.class);

    public ChatRepository(HibernateConf hibernateConf) {
        super(hibernateConf);
    }

    @Override
    public ChatEntity add(ChatEntity chatEntity) {
        return execute((session -> session.merge(chatEntity)), logger);
    }

    @Override
    public ChatEntity findById(long userId) {
        return execute((session) ->
                session.createQuery("SELECT ce FROM ChatEntity ce WHERE ce.chatId = :CHATID", ChatEntity.class)
                        .setParameter("CHATID", userId)
                        .getResultList()
                        .stream()
                        .findFirst()
                        .orElse(null), logger);
    }
}
