package com.example.gym.daos;

import com.example.gym.entities.Training;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrainingDAOTest {

    private TrainingDAO trainingDAO;

    @BeforeEach
    public void setUp() {
        trainingDAO = new TrainingDAO();
    }

    @Test
    public void testSave() {
        Training training = new Training();
        training.setTrainingName("Test Training");

        Training savedTraining = trainingDAO.save(training);

        assertEquals(training, savedTraining);
        assertTrue(trainingDAO.getById(savedTraining.getId()).isPresent());
    }

    @Test
    public void testGetById() {
        Training training = new Training();
        training.setTrainingName("Test Training");
        Training savedTraining = trainingDAO.save(training);

        Optional<Training> retrievedTraining = trainingDAO.getById(savedTraining.getId());

        assertTrue(retrievedTraining.isPresent());
        assertEquals(savedTraining, retrievedTraining.get());
    }

    @Test
    public void testGetAll() {
        Training training1 = new Training();
        training1.setTrainingName("Training 1");
        Training training2 = new Training();
        training2.setTrainingName("Training 2");

        trainingDAO.save(training1);
        trainingDAO.save(training2);

        List<Training> allTrainings = trainingDAO.getAll();

        assertEquals(2, allTrainings.size());
        assertTrue(allTrainings.contains(training1));
        assertTrue(allTrainings.contains(training2));
    }

    @Test
    public void testGetByIdNonExistent() {
        Optional<Training> retrievedTraining = trainingDAO.getById(123L);

        assertFalse(retrievedTraining.isPresent());
    }
}
