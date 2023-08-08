import entity.Trainee;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.TraineeRepository;
import service.TraineeService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TraineeServiceTest {

    @Mock
    private TraineeRepository traineeRepository;

    @InjectMocks
    private TraineeService traineeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGenerateTraineeProfile() {
        String firstName = "Alice";
        String lastName = "Smith";
        String address = "123 Main St";
        LocalDate dateOfBirth = LocalDate.of(1995, 6, 15);

        Trainee generatedTrainee = traineeService.generateTraineeProfile(firstName, lastName, address, dateOfBirth);

        assertEquals(firstName + "." + lastName, generatedTrainee.getUser().getUsername());
        assertEquals(address, generatedTrainee.getAddress());
        assertEquals(dateOfBirth, generatedTrainee.getDateOfBirth());
        // You can add more assertions as needed
    }

    @Test
    public void testSaveTraineeProfile() {
        Trainee trainee = new Trainee();
        traineeService.saveTraineeProfile(trainee);

        verify(traineeRepository, times(1)).save(trainee);
    }

    @Test
    public void testDeleteByID() {
        Long traineeId = 1L;
        traineeService.deleteByID(traineeId);

        verify(traineeRepository, times(1)).deleteById(traineeId);
    }
}
