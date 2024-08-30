package cs3220.hw2.controller;

import cs3220.hw2.model.Player;
import cs3220.hw2.model.Team;
import cs3220.hw2.repository.PlayerRepository;
import cs3220.hw2.repository.TeamRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class PlayerController {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public PlayerController(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @GetMapping("/players")
    public String players(Model model) {
        List<Player> players = (List<Player>) playerRepository.findAll();
        model.addAttribute("players", players);
        return "Players";
    }

    @GetMapping("/add-player")
    public String newPlayerForm(Model model) {
        List<Integer> years = getBirthYearList();
        //List<Team> teams = (List<Team>) teamRepository.findAll();
        model.addAttribute("player", new Player());
        model.addAttribute("years", years);
        //model.addAttribute("teams", teams);
        return "NewPlayer";
    }

    @PostMapping("/add-player")
    public String addPlayer(@ModelAttribute Player player) {
        player.setTeamId(null);
        playerRepository.save(player);
        return "redirect:/players";
    }

    @GetMapping("/edit-player")
    public String editPlayer(@RequestParam Integer playerId, Model model) {
        Optional<Player> player = playerRepository.findById(playerId);
        List<Integer> years = getBirthYearList();
        List<Team> teams = (List<Team>) teamRepository.findAll();

        model.addAttribute("player", player.orElse(null));
        model.addAttribute("years", years);
        model.addAttribute("teams", teams);
        return "EditPlayer";
    }

    @PostMapping("/edit-player")
    public String updatePlayer(@ModelAttribute Player player) {
        Optional<Player> existingPlayerOptional = playerRepository.findById(player.getId());
        if (existingPlayerOptional.isPresent()) {
            Player existingPlayer = existingPlayerOptional.get();
            existingPlayer.setName(player.getName());
            existingPlayer.setGender(player.getGender());
            existingPlayer.setBirthYear(player.getBirthYear());
            if (player.getTeamId() == null || player.getTeamId() == 0) {
                existingPlayer.setTeamId(null);
            } else {
                existingPlayer.setTeamId(player.getTeamId());
            }
            playerRepository.save(existingPlayer);
        }
        return "redirect:/players";
    }

    private List<Integer> getBirthYearList() {
        int currentYear = Year.now().getValue();
        return IntStream.rangeClosed(currentYear - 12, currentYear - 4)
                .boxed()
                .collect(Collectors.toList());
    }
}
