package com.example.gym.config;

import com.example.gym.storage.InMemoryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Configuration
@PropertySource("classpath:data-init.properties")
public class DataInitializationConfig {
    private final Environment environment;
    private final InMemoryStorage inMemoryStorage;

    @Autowired
    public DataInitializationConfig(Environment environment, InMemoryStorage inMemoryStorage) {
        this.environment = environment;
        this.inMemoryStorage = inMemoryStorage;
    }

    @PostConstruct
    public void initializeData() throws IOException {
        String filePath = environment.getProperty("data.init.file.path");
        List<String> lines = Files.readAllLines(Paths.get(filePath));

        for (String line : lines) {
            // Parse the line and populate in-memory storage
            // You need to implement parsing logic based on your data format
        }
    }
}
