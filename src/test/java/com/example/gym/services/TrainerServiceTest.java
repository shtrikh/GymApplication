package com.example.gym.services;

import com.example.gym.entities.Trainer;
import com.example.gym.enums.Specialization;
import com.example.gym.services.TrainerService;
import com.example.gym.utils.UsernamePasswordUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TrainerServiceTest {

    private TrainerService trainerService;

    @Mock
    private UsernamePasswordUtil usernamePasswordUtil;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        trainerService = new TrainerService();
        trainerService.setUsernamePasswordUtil(usernamePasswordUtil);
    }

    @Test
    public void testCreateTrainerProfile() {
        when(usernamePasswordUtil.calculateUsername("Alice", "Smith")).thenReturn("alicesmith");
        when(usernamePasswordUtil.generateRandomPassword(10)).thenReturn("randomPass");

        Trainer trainer = trainerService.createTrainerProfile("Alice", "Smith", Specialization.STRENGTH_TRAINING);

        assertEquals("Alice", trainer.getUser().getFirstName());
        assertEquals("Smith", trainer.getUser().getLastName());
        assertEquals("alicesmith", trainer.getUser().getUsername());
        assertEquals("randomPass", trainer.getUser().getPassword());
        assertEquals(Specialization.STRENGTH_TRAINING, trainer.getSpecialization());

        verify(usernamePasswordUtil, times(1)).calculateUsername("Alice", "Smith");
        verify(usernamePasswordUtil, times(1)).generateRandomPassword(10);
    }

    @Test
    public void testUpdateTrainer() {
        when(usernamePasswordUtil.calculateUsername("Bob", "Johnson")).thenReturn("bobjohnson");
        when(usernamePasswordUtil.generateRandomPassword(10)).thenReturn("randomPass");

        Trainer updatedTrainer = trainerService.updateTrainer(
                1L, "Bob", "Johnson", Specialization.CARDIO_TRAINING
        );

        assertEquals(1L, updatedTrainer.getId());
        assertEquals("Bob", updatedTrainer.getUser().getFirstName());
        assertEquals("Johnson", updatedTrainer.getUser().getLastName());
        assertEquals("bobjohnson", updatedTrainer.getUser().getUsername());
        assertEquals("randomPass", updatedTrainer.getUser().getPassword());
        assertEquals(Specialization.CARDIO_TRAINING, updatedTrainer.getSpecialization());

        verify(usernamePasswordUtil, times(1)).calculateUsername("Bob", "Johnson");
        verify(usernamePasswordUtil, times(1)).generateRandomPassword(10);
    }

}

