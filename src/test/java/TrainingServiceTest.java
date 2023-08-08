import entity.Training;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.TrainingRepository;
import service.TrainingService;

import static org.mockito.Mockito.*;

public class TrainingServiceTest {

    @Mock
    private TrainingRepository trainingRepository;

    @InjectMocks
    private TrainingService trainingService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveTraining() {
        Training training = new Training();
        trainingService.saveTraining(training);

        verify(trainingRepository, times(1)).save(training);
    }

    // Add more test methods as needed

    @Test
    public void testGetById() {
        Long trainingId = 1L;
        when(trainingRepository.getReferenceById(trainingId)).thenReturn(new Training());

        Training retrievedTraining = trainingService.getById(trainingId);

        // Assertions for the retrieved training
    }
}
