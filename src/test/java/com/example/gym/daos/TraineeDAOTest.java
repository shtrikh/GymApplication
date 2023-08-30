package com.example.gym.daos;

import com.example.gym.daos.TraineeDAO;
import com.example.gym.entities.Trainee;
import com.example.gym.entities.User;
import com.example.gym.services.TraineeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TraineeDAOTest {

    @Mock
    private TraineeService traineeService;

    private TraineeDAO traineeDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        traineeDAO = new TraineeDAO(traineeService);
    }

    @Test
    public void testSaveTrainee() {
        User user = new User(); // Set up user properties
        user.setFirstName("John");
        user.setLastName("Doe");
        LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);
        String address = "123 Main St";
        Trainee trainee = new Trainee();
        trainee.setUser(user);
        trainee.setDateOfBirth(dateOfBirth);
        trainee.setAddress(address);

        when(traineeService.createTraineeProfile(any(), any(), any(), any())).thenReturn(trainee);

        Trainee savedTrainee = traineeDAO.save(trainee);

        assertNotNull(savedTrainee);
        verify(traineeService, times(1)).createTraineeProfile(any(), any(), any(), any());
    }

    @Test
    public void testGetById() {
        User user = new User(); // Set up user properties
        user.setFirstName("John");
        user.setLastName("Doe");
        LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);
        String address = "123 Main St";
        Trainee trainee = new Trainee();
        trainee.setUser(user);
        trainee.setDateOfBirth(dateOfBirth);
        trainee.setAddress(address);
        long traineeId = 1L;
        trainee.setId(traineeId);
        when(traineeService.createTraineeProfile(any(), any(), any(), any())).thenReturn(trainee);
        traineeDAO.save(trainee);

        Optional<Trainee> retrievedTrainee = traineeDAO.getById(traineeId);

        assertTrue(retrievedTrainee.isPresent());
        assertEquals(traineeId, retrievedTrainee.get().getId());
    }

    @Test
    public void testGetAll() {
        User user1 = new User(); // Set up user properties
        user1.setFirstName("John");
        user1.setLastName("Doe");
        LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);
        String address = "123 Main St";
        Trainee trainee1 = new Trainee();
        trainee1.setUser(user1);
        trainee1.setDateOfBirth(dateOfBirth);
        trainee1.setAddress(address);
        User user = new User(); // Set up user properties
        user.setFirstName("Piter");
        user.setLastName("Goe");
        LocalDate dateOfBirth2 = LocalDate.of(1992, 2, 2);
        String address2 = "122 Main St";
        Trainee trainee2 = new Trainee();
        trainee2.setUser(user);
        trainee2.setDateOfBirth(dateOfBirth2);
        trainee2.setAddress(address2);
        when(traineeService.createTraineeProfile(any(), any(), any(), any())).thenReturn(trainee1, trainee2);
        traineeDAO.save(trainee1);
        traineeDAO.save(trainee2);

        List<Trainee> allTrainees = traineeDAO.getAll();

        assertEquals(2, allTrainees.size());
    }

    @Test
    public void testUpdate() {
        User user = new User(); // Set up user properties
        user.setFirstName("John");
        user.setLastName("Doe");
        LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);
        String address = "123 Main St";
        Trainee trainee = new Trainee();
        trainee.setUser(user);
        trainee.setDateOfBirth(dateOfBirth);
        trainee.setAddress(address);
        trainee.setId(1L);
        when(traineeService.createTraineeProfile(any(), any(), any(), any())).thenReturn(trainee);
        traineeDAO.save(trainee);

        User updatedUser = new User(); // Set up user properties
        updatedUser.setFirstName("Piter");
        updatedUser.setLastName("Doe");
        LocalDate dateOfBirth2 = LocalDate.of(1990, 2, 1);
        String address2 = "122 Main St";
        Trainee updatedTrainee = new Trainee();
        updatedTrainee.setUser(updatedUser);
        updatedTrainee.setDateOfBirth(dateOfBirth2);
        updatedTrainee.setAddress(address2);
        updatedTrainee.setId(1L);
        when(traineeService.updateTrainee(anyLong(), any(), any(), any(), any())).thenReturn(updatedTrainee);

        Trainee result = traineeDAO.update(updatedTrainee);

        assertNotNull(result);
        assertEquals(updatedTrainee.getId(), result.getId());
        assertEquals(updatedTrainee.getUser().getUsername(), result.getUser().getUsername());
    }

    @Test
    public void testDeleteById() {
        User user = new User(); // Set up user properties
        user.setFirstName("John");
        user.setLastName("Doe");
        LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);
        String address = "123 Main St";
        Trainee trainee = new Trainee();
        trainee.setUser(user);
        trainee.setDateOfBirth(dateOfBirth);
        trainee.setAddress(address);
        long traineeId = 1L;
        trainee.setId(traineeId);
        when(traineeService.createTraineeProfile(any(), any(), any(), any())).thenReturn(trainee);
        traineeDAO.save(trainee);

        boolean isDeleted = traineeDAO.deleteById(traineeId);

        assertTrue(isDeleted);
        assertFalse(traineeDAO.getById(traineeId).isPresent());
    }
}
