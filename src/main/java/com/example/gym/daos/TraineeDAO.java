package com.example.gym.daos;

import com.example.gym.entities.Trainee;
import com.example.gym.utils.UsernamePasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
    private final UsernamePasswordUtil util;

    public Trainee save(Trainee trainee) {
        if (trainee.getId() == null) {
            trainee.setId(nextId++);
        }
        trainee.getUser().setUsername(util.calculateUsername(trainee.getUser().getFirstName(), trainee.getUser().getLastName()));
        trainee.getUser().setPassword(util.generateRandomPassword(10));
        trainees.put(trainee.getId(), trainee);
        return trainee;
    }

    public Optional<Trainee> getById(Long id) {
        return Optional.ofNullable(trainees.get(id));
    }

    public List<Trainee> getAll() {
        return new ArrayList<>(trainees.values());
    }

    public boolean deleteById(Long id) {
        Trainee removedTrainee = trainees.remove(id);
        return removedTrainee != null;
    }
}
