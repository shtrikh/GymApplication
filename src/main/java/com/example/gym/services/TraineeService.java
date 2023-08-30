package com.example.gym.services;

import com.example.gym.entities.Trainee;
import com.example.gym.entities.User;
import com.example.gym.utils.UsernamePasswordUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TraineeService {
    private static final Logger logger = LogManager.getLogger(TraineeService.class);

    private UsernamePasswordUtil usernamePasswordUtil;

    @Autowired
    public void setUsernamePasswordUtil(UsernamePasswordUtil usernamePasswordUtil) {
        this.usernamePasswordUtil = usernamePasswordUtil;
    }

    public Trainee createTraineeProfile(String firstName, String lastName, LocalDate dateOfBirth, String address) {
        logger.info("Creating trainee profile...");
        String username = usernamePasswordUtil.calculateUsername(firstName, lastName);
        String password = usernamePasswordUtil.generateRandomPassword(10);

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        Trainee trainee = new Trainee();
        trainee.setUser(user);
        trainee.setDateOfBirth(dateOfBirth);
        trainee.setAddress(address);

        logger.info("Trainee profile created: {}", trainee);
        return trainee;
    }

    public Trainee updateTrainee(Long traineeId, String firstName, String lastName, LocalDate dateOfBirth, String address) {
        logger.info("Updating trainee profile...");
        Trainee trainee = createTraineeProfile(firstName, lastName, dateOfBirth, address);
        trainee.setId(traineeId);

        logger.info("Trainee profile updated: {}", trainee);
        return trainee;
    }
}
