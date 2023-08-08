package repository;

import entity.Trainee;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraineeRepository  extends JpaRepository<Trainee, Long> {

}
