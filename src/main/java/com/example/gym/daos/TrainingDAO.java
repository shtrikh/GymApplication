package com.example.gym.daos;

import com.example.gym.entities.Training;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Repository
public class TrainingDAO {
    private final Map<Long, Training> trainings = new HashMap<>();
    private long nextId = 1;
    private static final Logger logger = LogManager.getLogger(TrainingDAO.class);


    public Training save(Training training) {
        if (training.getId() == null) {
            training.setId(nextId++);
        }
        trainings.put(training.getId(), training);
        logger.info("Training saved: {}", training);
        return training;
    }

    public Optional<Training> getById(Long id) {
        logger.info("Getting training by ID: {}", id);
        return Optional.ofNullable(trainings.get(id));
    }

    public List<Training> getAll() {
        logger.info("Getting all trainings");
        return new ArrayList<>(trainings.values());
    }

}
