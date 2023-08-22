package com.example.gym.services;

import com.example.gym.entities.Trainee;
import com.example.gym.utils.UsernamePasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TraineeService {
    private final UsernamePasswordUtil usernamePasswordUtil;

    @Autowired
    public TraineeService(UsernamePasswordUtil usernamePasswordUtil) {
        this.usernamePasswordUtil = usernamePasswordUtil;
    }

    public Trainee createTraineeProfile(String firstName, String lastName, LocalDate dateOfBirth, String address) {
        String username = usernamePasswordUtil.calculateUsername(firstName, lastName);
        String password = usernamePasswordUtil.generateRandomPassword(10);

        // Create Trainee profile with calculated username and generated password
        // You need to implement the logic to create and store Trainee entity

        Trainee trainee = new Trainee();
        trainee.getUser().setUsername(username);
        trainee.getUser().setPassword(password);
        trainee.getUser().setFirstName(firstName);
        trainee.getUser().setLastName(lastName);
        trainee.setDateOfBirth(dateOfBirth);
        trainee.setAddress(address);

        // Save the trainee entity using your TraineeDAO or repository

        return trainee;
    }
}
