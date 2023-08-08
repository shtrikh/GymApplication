import entity.Trainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.TrainerRepository;
import service.TrainerService;

import static org.mockito.Mockito.*;

public class TrainerServiceTest {

    @Mock
    private TrainerRepository trainerRepository;

    @InjectMocks
    private TrainerService trainerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGenerateTrainerProfile() {
        String firstName = "John";
        String lastName = "Doe";
        String specialization = "Fitness";

        Trainer generatedTrainer = trainerService.generateTrainerProfile(firstName, lastName, specialization);

        // Assertions for the generated trainer profile
        // (You should verify that the user details, specialization, etc., are correctly set)
    }

    @Test
    public void testSaveTrainerProfile() {
        Trainer trainer = new Trainer();
        trainerService.saveTrainerProfile(trainer);

        verify(trainerRepository, times(1)).save(trainer);
    }

    // Add more test methods as needed

    @Test
    public void testGetById() {
        Long trainerId = 1L;
        when(trainerRepository.getReferenceById(trainerId)).thenReturn(new Trainer());

        Trainer retrievedTrainer = trainerService.getById(trainerId);

        // Assertions for the retrieved trainer
    }
}
