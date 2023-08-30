package com.example.gym.services;

import com.example.gym.entities.Trainee;
import com.example.gym.services.TraineeService;
import com.example.gym.utils.UsernamePasswordUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TraineeServiceTest {

    private TraineeService traineeService;

    @Mock
    private UsernamePasswordUtil usernamePasswordUtil;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        traineeService = new TraineeService();
        traineeService.setUsernamePasswordUtil(usernamePasswordUtil);
    }

    @Test
    public void testCreateTraineeProfile() {
        when(usernamePasswordUtil.calculateUsername("John", "Doe")).thenReturn("johndoe");
        when(usernamePasswordUtil.generateRandomPassword(10)).thenReturn("randomPass");

        Trainee trainee = traineeService.createTraineeProfile("John", "Doe", LocalDate.of(1990, 1, 1), "123 Main St");

        assertEquals("John", trainee.getUser().getFirstName());
        assertEquals("Doe", trainee.getUser().getLastName());
        assertEquals("johndoe", trainee.getUser().getUsername());
        assertEquals("randomPass", trainee.getUser().getPassword());
        assertEquals(LocalDate.of(1990, 1, 1), trainee.getDateOfBirth());
        assertEquals("123 Main St", trainee.getAddress());

        verify(usernamePasswordUtil, times(1)).calculateUsername("John", "Doe");
        verify(usernamePasswordUtil, times(1)).generateRandomPassword(10);
    }

    @Test
    public void testUpdateTrainee() {
        when(usernamePasswordUtil.calculateUsername("John", "Doe")).thenReturn("johndoe");
        when(usernamePasswordUtil.generateRandomPassword(10)).thenReturn("randomPass");

        Trainee updatedTrainee = traineeService.updateTrainee(
                1L, "John", "Doe", LocalDate.of(1990, 1, 1), "123 Main St"
        );

        assertEquals(1L, updatedTrainee.getId());
        assertEquals("John", updatedTrainee.getUser().getFirstName());
        assertEquals("Doe", updatedTrainee.getUser().getLastName());
        assertEquals("johndoe", updatedTrainee.getUser().getUsername());
        assertEquals("randomPass", updatedTrainee.getUser().getPassword());
        assertEquals(LocalDate.of(1990, 1, 1), updatedTrainee.getDateOfBirth());
        assertEquals("123 Main St", updatedTrainee.getAddress());

        verify(usernamePasswordUtil, times(1)).calculateUsername("John", "Doe");
        verify(usernamePasswordUtil, times(1)).generateRandomPassword(10);
    }

}

