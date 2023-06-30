package ru.example.jpa.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.example.jpa.config.HibernateConf;
import ru.example.jpa.entities.UserEntity;
import ru.example.jpa.mapping.UserMapper;
import ru.example.jpa.repositories.interfaces.IUserRepository;
import ru.example.model.dto.UserDto;

import java.util.Optional;

@Repository
public class UserRepository extends CrudRepository implements IUserRepository {
    private final Logger logger = LoggerFactory.getLogger(ChatRepository.class);

    public UserRepository(HibernateConf hibernateConf) {
        super(hibernateConf);
    }

    @Override
    public UserEntity add(UserEntity userEntity) {
        return execute((session -> session.merge(userEntity)), logger);
    }

    @Override
    public UserEntity findById(long userId) {
        return execute((session) ->
                session.createQuery("SELECT ue FROM UserEntity ue WHERE ue.userId = :USERID", UserEntity.class)
                        .setParameter("USERID", userId)
                        .getResultList()
                        .stream()
                        .findFirst()
                        .orElse(null), logger);
    }
}
