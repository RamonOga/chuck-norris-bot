package ru.example.jpa.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.example.bot.config.HibernateConf;
import ru.example.jpa.mapping.UserMapper;
import ru.example.model.dto.UserDto;

@Repository
public class UserRepository extends CrudRepository {
    private final Logger logger = LoggerFactory.getLogger(ChatRepository.class);

    public UserRepository(HibernateConf hibernateConf) {
        super(hibernateConf);
    }

    public void add(UserDto userDto) {
        execute((session -> session.merge(UserMapper.map(userDto))), logger);
    }
}
