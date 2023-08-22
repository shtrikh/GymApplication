package com.example.gym.daos;

import com.example.gym.entities.Trainer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class TrainerDAO {
    private final Map<Long, Trainer> trainers = new HashMap<>();
    private long nextId = 1;

    public Trainer save(Trainer trainer) {
        if (trainer.getId() == null) {
            trainer.setId(nextId++);
        }
        trainers.put(trainer.getId(), trainer);
        return trainer;
    }

    public Optional<Trainer> getById(Long id) {
        return Optional.ofNullable(trainers.get(id));
    }

    public List<Trainer> getAll() {
        return new ArrayList<>(trainers.values());
    }

    // Other methods for updating, deleting, and more
}
