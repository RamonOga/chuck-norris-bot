package ru.example.model.dto;

import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@Getter
public class PhotoDto {
    private final String id;
    private final String fileUniqueId;
    private final Integer width;
    private final Integer height;
    private final Integer fileSize;
    private final String filePath;
}

