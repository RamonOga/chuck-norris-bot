package ru.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"ru.example.jpa.entities"})
public class ChuckNorrisBotApplication {
    private static final Logger logger = LoggerFactory.getLogger(ChuckNorrisBotApplication.class);
    public static void main(String[] args) {
        logger.info("application started");
        SpringApplication.run(ChuckNorrisBotApplication.class, args);

    }
}
