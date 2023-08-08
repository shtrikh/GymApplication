package service;

import entity.Training;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import repository.TrainingRepository;

@Service
@RequiredArgsConstructor
public class TrainingService {
    private final TrainingRepository trainingRepository;
    private final Logger logger = LoggerFactory.getLogger(TrainingService.class); // Initialize logger

    public void saveTraining(Training training) {
        logger.info("Saving Training: {}", training);
        trainingRepository.save(training);
        logger.info("Training saved: {}", training.getId());
    }

    public Training getById(Long id) {
        logger.info("Fetching Training by ID: {}", id);
        return trainingRepository.getReferenceById(id);
    }
}
