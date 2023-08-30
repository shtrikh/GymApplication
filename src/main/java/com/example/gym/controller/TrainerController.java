package com.example.gym.controller;

import com.example.gym.daos.TrainerDAO;
import com.example.gym.entities.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/trainers")
public class TrainerController {
    private final TrainerDAO trainerDAO;

    @Autowired
    public TrainerController(TrainerDAO trainerDAO) {
        this.trainerDAO = trainerDAO;
    }

    @PostMapping
    public ResponseEntity<Trainer> saveTrainer(@RequestBody Trainer trainer) {
        Trainer savedTrainer = trainerDAO.save(trainer);
        return new ResponseEntity<>(savedTrainer, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trainer> getTrainerById(@PathVariable Long id) {
        Optional<Trainer> trainer = trainerDAO.getById(id);
        return trainer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Trainer>> getAllTrainers() {
        List<Trainer> trainers = trainerDAO.getAll();
        return new ResponseEntity<>(trainers, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Trainer> updateTrainer(@PathVariable Long id, @RequestBody Trainer trainer) {
        trainer.setId(id);
        Trainer updatedTrainer = trainerDAO.update(trainer);

        return new ResponseEntity<>(updatedTrainer, HttpStatus.OK);
    }

}
