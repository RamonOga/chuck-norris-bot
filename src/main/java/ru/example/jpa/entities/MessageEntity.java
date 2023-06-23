package ru.example.jpa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "messages")
@Getter
@Setter
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private Integer messageId;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id")
    private UserEntity userId;
    private Integer date;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "chat_id")
    private ChatEntity chatId;
    private String text;
//    private Integer photoId;
//    private Integer videoId;
    private String caption;
    private String messageType;
}
