package ru.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatTelegramBotApplication {
    private static final Logger logger = LoggerFactory.getLogger(ChatTelegramBotApplication.class);
    public static void main(String[] args) {
        logger.info("application started");
        SpringApplication.run(ChatTelegramBotApplication.class, args);
    }
}
