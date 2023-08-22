package com.example.gym.storage;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InMemoryStorage {
    private final Map<Long, Object> dataMap = new HashMap<>();

    public Map<Long, Object> getDataMap() {
        return dataMap;
    }
}
