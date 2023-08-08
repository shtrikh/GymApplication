package entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trainee_id")
    private Trainee trainee;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    private String trainingName;

    @ManyToOne
    @JoinColumn(name = "training_type_id")
    private TrainingType trainingType;

    private LocalDate trainingDate;
    private int trainingDuration;
}

