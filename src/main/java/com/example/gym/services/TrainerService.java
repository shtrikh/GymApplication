package com.example.gym.services;

import com.example.gym.entities.Trainer;
import com.example.gym.entities.User;
import com.example.gym.enums.Specialization;
import com.example.gym.utils.UsernamePasswordUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainerService {
    private static final Logger logger = LogManager.getLogger(TrainerService.class);

    private UsernamePasswordUtil usernamePasswordUtil;

    @Autowired
    public void setUsernamePasswordUtil(UsernamePasswordUtil usernamePasswordUtil) {
        this.usernamePasswordUtil = usernamePasswordUtil;
    }

    public Trainer createTrainerProfile(String firstName, String lastName, Specialization specialization) {
        logger.info("Creating trainer profile...");
        String username = usernamePasswordUtil.calculateUsername(firstName, lastName);
        String password = usernamePasswordUtil.generateRandomPassword(10);

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        Trainer trainer = new Trainer();
        trainer.setUser(user);
        trainer.setSpecialization(specialization);

        logger.info("Trainer profile created: {}", trainer);
        return trainer;
    }

    public Trainer updateTrainer(Long trainerId, String firstName, String lastName, Specialization specialization) {
        logger.info("Updating trainer profile...");
        Trainer trainer = createTrainerProfile(firstName, lastName, specialization);
        trainer.setId(trainerId);

        logger.info("Trainer profile updated: {}", trainer);
        return trainer;
    }
}
