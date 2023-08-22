package com.example.gym.daos;

import com.example.gym.entities.Training;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class TrainingDAO {
    private final Map<Long, Training> trainings = new HashMap<>();
    private long nextId = 1;

    public Training save(Training training) {
        if (training.getId() == null) {
            training.setId(nextId++);
        }
        trainings.put(training.getId(), training);
        return training;
    }

    public Optional<Training> getById(Long id) {
        return Optional.ofNullable(trainings.get(id));
    }

    public List<Training> getAll() {
        return new ArrayList<>(trainings.values());
    }

    // Other methods for updating, deleting, and more
}
