package com.example.gym.daos;

import com.example.gym.daos.TrainerDAO;
import com.example.gym.entities.Trainer;
import com.example.gym.entities.User;
import com.example.gym.enums.Specialization;
import com.example.gym.services.TrainerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrainerDAOTest {

    @Mock
    private TrainerService trainerService;

    private TrainerDAO trainerDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        trainerDAO = new TrainerDAO(trainerService);
    }

    @Test
    void saveTrainer() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        Trainer trainer = new Trainer();
        trainer.setUser(user);
        trainer.setSpecialization(Specialization.WORKOUT);
        when(trainerService.createTrainerProfile(any(), any(), any())).thenReturn(trainer);

        Trainer savedTrainer = trainerDAO.save(trainer);

        assertNotNull(savedTrainer.getId());
        verify(trainerService).createTrainerProfile(any(), any(), any());
    }

    @Test
    void updateTrainer() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        Trainer trainer = new Trainer();
        trainer.setUser(user);
        trainer.setSpecialization(Specialization.WORKOUT);
        trainer.setId(1L);

        User updatedUser = new User();
        updatedUser.setFirstName("Piter");
        updatedUser.setLastName("Goe");
        Trainer updatedTrainer = new Trainer();
        updatedTrainer.setUser(updatedUser);
        updatedTrainer.setSpecialization(Specialization.WORKOUT);
        updatedTrainer.setId(1L);
        when(trainerService.updateTrainer(anyLong(), any(), any(), any())).thenReturn(updatedTrainer);

        Trainer result = trainerDAO.update(updatedTrainer);

        assertSame(updatedTrainer.getUser().getUsername(), result.getUser().getUsername());
        verify(trainerService).updateTrainer(anyLong(), any(), any(), any());
    }

    @Test
    void getById() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        Trainer trainer = new Trainer();
        trainer.setUser(user);
        trainer.setSpecialization(Specialization.WORKOUT);
        trainer.setId(1L);
        trainerDAO.save(trainer);

        Optional<Trainer> foundTrainer = trainerDAO.getById(1L);

        assertTrue(foundTrainer.isPresent());
        assertEquals(1L, foundTrainer.get().getId());
    }

    @Test
    void getAll() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        Trainer trainer = new Trainer();
        trainer.setUser(user);
        trainer.setSpecialization(Specialization.WORKOUT);
        trainer.setId(1L);
        trainerDAO.save(trainer);

        List<Trainer> trainers = trainerDAO.getAll();

        assertEquals(1, trainers.size());
        assertEquals(1L, trainers.get(0).getId());
    }

    @Test
    void deleteById() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        Trainer trainer = new Trainer();
        trainer.setUser(user);
        trainer.setSpecialization(Specialization.WORKOUT);
        trainer.setId(1L);
        trainerDAO.save(trainer);
        User user2 = new User();
        user2.setFirstName("John");
        user2.setLastName("Doe");
        Trainer trainer2 = new Trainer();
        trainer2.setUser(user);
        trainer2.setSpecialization(Specialization.WORKOUT);
        trainer2.setId(2L);
        trainerDAO.save(trainer);

        assertTrue(trainerDAO.deleteById(1L));
        assertFalse(trainerDAO.deleteById(2L));
        assertTrue(trainerDAO.getAll().isEmpty());
    }
}
