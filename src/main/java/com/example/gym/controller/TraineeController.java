package com.example.gym.controller;

import com.example.gym.daos.TraineeDAO;
import com.example.gym.entities.Trainee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/trainees")
public class TraineeController {
    private final TraineeDAO traineeDAO;

    @Autowired
    public TraineeController(TraineeDAO traineeDAO) {
        this.traineeDAO = traineeDAO;
    }

    @PostMapping
    public ResponseEntity<Trainee> saveTrainee(@RequestBody Trainee trainee) {
        Trainee savedTrainee = traineeDAO.save(trainee);
        return new ResponseEntity<>(savedTrainee, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trainee> getTraineeById(@PathVariable Long id) {
        Optional<Trainee> trainee = traineeDAO.getById(id);
        return trainee.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Trainee>> getAllTrainees() {
        List<Trainee> trainees = traineeDAO.getAll();
        return new ResponseEntity<>(trainees, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTraineeById(@PathVariable Long id) {
        boolean deleted = traineeDAO.deleteById(id);
        if (deleted) {
            return new ResponseEntity<>("Trainee deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Trainee not found", HttpStatus.NOT_FOUND);
        }
    }

}

