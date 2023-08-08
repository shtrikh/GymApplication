package service;

import entity.Trainee;
import entity.User;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import repository.TraineeRepository;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class TraineeService {
    private final TraineeRepository traineeRepository;
    private final Logger logger = LogManager.getLogger(TraineeService.class); // Initialize logger

    public void saveTraineeProfile(Trainee trainee) {
        traineeRepository.save(trainee);
        logger.info("Trainee profile saved: {}", trainee.getId());
    }

    public Trainee generateTraineeProfile(String firstName, String lastName, String address, LocalDate dateOfBirth) {
        logger.debug("Generating Trainee profile...");
        User user = new User();
        Trainee trainee = new Trainee();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(firstName + "." + lastName);
        user.setPassword(generatePassword());

        trainee.setAddress(address);
        trainee.setDateOfBirth(dateOfBirth);
        trainee.setUser(user);

        logger.debug("Trainee profile generated: {}", trainee);
        return trainee;
    }

    public static String generatePassword() {
        SecureRandom random = new SecureRandom();
        byte[] randomBytes = new byte[10];
        random.nextBytes(randomBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }

    public void deleteByID(Long id){
        traineeRepository.deleteById(id);
        logger.info("Trainee profile deleted: {}", id);
    }
}
