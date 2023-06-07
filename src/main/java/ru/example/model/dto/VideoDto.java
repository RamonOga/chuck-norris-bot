package ru.example.model.dto;

import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@Getter
public class VideoDto {
    private final String id;
    private final String fileUniqueId;
    private final Integer width;
    private final Integer height;
    private final Integer duration;
    private final PhotoDto thumb;
    private final String mimeType;
    private final Long fileSize;
    private final String fileName;
    private byte[] bytes;

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
