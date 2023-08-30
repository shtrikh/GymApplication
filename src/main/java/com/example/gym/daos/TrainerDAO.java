package com.example.gym.daos;

import com.example.gym.entities.Trainer;
import com.example.gym.services.TrainerService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TrainerDAO {
    private static final Logger logger = LogManager.getLogger(TrainerDAO.class);

    private final Map<Long, Trainer> trainers = new HashMap<>();
    private long nextId = 1;

    private final TrainerService trainerService;

    public Trainer save(Trainer trainer) {
        logger.info("Saving trainer: {}", trainer);

        Trainer currentTrainer = trainerService.createTrainerProfile(trainer.getUser().getFirstName(), trainer.getUser().getLastName(),
                trainer.getSpecialization());
        if (currentTrainer.getId() == null) {
            currentTrainer.setId(nextId++);
        }
        trainers.put(trainer.getId(), currentTrainer);
        return currentTrainer;
    }

    public Trainer update(Trainer trainer) {
        logger.info("Updating trainer: {}", trainer);

        deleteById(trainer.getId());
        save(trainerService.updateTrainer(trainer.getId(), trainer.getUser().getFirstName(),
                trainer.getUser().getLastName(), trainer.getSpecialization()));
        return trainer;
    }


    public Optional<Trainer> getById(Long id) {
        logger.info("Getting trainer by id: {}", id);
        return Optional.ofNullable(trainers.get(id));
    }

    public List<Trainer> getAll() {
        logger.info("Getting all trainers");
        return new ArrayList<>(trainers.values());
    }

    public boolean deleteById(Long id) {
        logger.info("Deleting trainer by id: {}", id);
        Trainer removedTrainer = trainers.remove(id);
        return removedTrainer != null;
    }
}
