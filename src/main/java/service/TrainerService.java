package service;

import entity.Trainer;
import entity.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import repository.TrainerRepository;

import java.security.SecureRandom;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class TrainerService {
    private final TrainerRepository trainerRepository;
    private final Logger logger = LoggerFactory.getLogger(TrainerService.class); // Initialize logger

    public void saveTrainerProfile(Trainer trainer) {
        logger.info("Saving Trainer profile: {}", trainer);
        trainerRepository.save(trainer);
        logger.info("Trainer profile saved: {}", trainer.getId());
    }

    public Trainer generateTrainerProfile(String firstName, String lastName, String specialization) {
        logger.debug("Generating Trainer profile...");
        User user = new User();
        Trainer trainer = new Trainer();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(firstName + "." + lastName);
        user.setPassword(generatePassword());

        trainer.setSpecialization(specialization);
        trainer.setUser(user);

        logger.debug("Trainer profile generated: {}", trainer);
        return trainer;
    }

    public static String generatePassword() {
        SecureRandom random = new SecureRandom();
        byte[] randomBytes = new byte[10];
        random.nextBytes(randomBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }

    public Trainer getById(Long id) {
        logger.info("Fetching Trainer by ID: {}", id);
        return trainerRepository.getReferenceById(id);
    }
}
