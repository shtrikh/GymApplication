package com.example.gym.daos;

import com.example.gym.entities.Trainee;
import com.example.gym.services.TraineeService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TraineeDAO {
    private final Map<Long, Trainee> trainees = new HashMap<>();
    private long nextId = 1;
    private final TraineeService traineeService;
    private static final Logger logger = LogManager.getLogger(TraineeDAO.class);

    public Trainee save(Trainee trainee) {
        logger.info("Saving trainee: {}", trainee);
        Trainee currentTrainee = traineeService.createTraineeProfile(trainee.getUser().getFirstName(), trainee.getUser().getLastName(),
                trainee.getDateOfBirth(), trainee.getAddress());
        if (currentTrainee.getId() == null) {
            currentTrainee.setId(nextId++);
        }
        trainees.put(trainee.getId(), currentTrainee);
        return currentTrainee;
    }

    public Optional<Trainee> getById(Long id) {
        logger.info("Getting trainee by ID: {}", id);
        return Optional.ofNullable(trainees.get(id));
    }

    public List<Trainee> getAll() {
        logger.info("Getting all trainees");
        return new ArrayList<>(trainees.values());
    }

    public Trainee update(Trainee trainee) {
        logger.info("Updating trainee: {}", trainee);
        deleteById(trainee.getId());
        save(traineeService.updateTrainee(trainee.getId(), trainee.getUser().getFirstName(),
                trainee.getUser().getLastName(), trainee.getDateOfBirth(), trainee.getAddress()));
        return trainee;
    }

    public boolean deleteById(Long id) {
        logger.info("Deleting trainee with ID: {}", id);
        Trainee removedTrainee = trainees.remove(id);
        return removedTrainee != null;
    }
}
