package ru.example.jpa.mapping;

import ru.example.jpa.entities.UserEntity;
import ru.example.model.dto.UserDto;

public class UserMapper {
    public static UserDto map(UserEntity entity) {
        return UserDto.builder()
                .id(entity.getUserId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .username(entity.getUserName())
                .isBot(entity.getIsBot())
                .build();

    }
    public static UserEntity map(UserDto dto) {
        UserEntity entity = new UserEntity();
        entity.setUserId(dto.getId());
        entity.setUserName(dto.getUsername());
        entity.setLastName(dto.getLastName());
        entity.setFirstName(dto.getFirstName());
        entity.setIsBot(dto.getIsBot());
        return entity;
    }
}
