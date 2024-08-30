package cs3220.hw2.repository;

import cs3220.hw2.model.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Integer> {
}
