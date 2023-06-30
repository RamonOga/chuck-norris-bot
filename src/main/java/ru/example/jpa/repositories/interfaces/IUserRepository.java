package ru.example.jpa.repositories.interfaces;

import ru.example.jpa.entities.UserEntity;

import java.util.Optional;

public interface IUserRepository {
    UserEntity add(UserEntity userEntity);
    UserEntity findById(long userId);
}
