package ru.example.model.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MessageType {
    TEXT("text"),
    VIDEO("video"),
    PHOTO("photo");
    private final String name;
}
