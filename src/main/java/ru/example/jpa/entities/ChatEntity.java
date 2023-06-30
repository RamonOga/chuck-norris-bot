package ru.example.jpa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "chat")
@Setter
@Getter
public class ChatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "chatId")
    private long chatId;
    @Column(name = "messageType")
    private String messageType;
}
