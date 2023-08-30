package com.example.gym.controller;

import com.example.gym.daos.TrainingDAO;
import com.example.gym.entities.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/trainings")
public class TrainingController {
    private final TrainingDAO trainingDAO;

    @Autowired
    public TrainingController(TrainingDAO trainingDAO) {
        this.trainingDAO = trainingDAO;
    }

    @PostMapping
    public ResponseEntity<Training> saveTraining(@RequestBody Training training) {
        Training savedTraining = trainingDAO.save(training);
        return new ResponseEntity<>(savedTraining, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Training> getTrainingById(@PathVariable Long id) {
        Optional<Training> training = trainingDAO.getById(id);
        return training.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Training>> getAllTrainings() {
        List<Training> trainings = trainingDAO.getAll();
        return new ResponseEntity<>(trainings, HttpStatus.OK);
    }
}
