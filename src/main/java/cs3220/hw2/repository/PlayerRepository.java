package cs3220.hw2.repository;

import cs3220.hw2.model.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlayerRepository extends CrudRepository<Player, Integer> {

    @Query("SELECT p FROM Player p WHERE p.teamId = ?1")
    List<Player> findByTeamId(Integer teamId);
}
