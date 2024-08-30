package cs3220.hw2.controller;

import cs3220.hw2.model.Player;
import cs3220.hw2.model.Team;
import cs3220.hw2.repository.PlayerRepository;
import cs3220.hw2.repository.TeamRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
//@RequestMapping("/teams")
public class TeamController {
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    public TeamController(TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    @GetMapping("/teams")
    public String teams(Model model) {
        List<Team> teams = (List<Team>) teamRepository.findAll();
        List<Integer> playerCounts = new ArrayList<>();
        for (Team team : teams) {
            List<Player> players = playerRepository.findByTeamId(team.getId());
            int playerCount = players.size();
            playerCounts.add(playerCount);
        }
        model.addAttribute("teams", teams);
        model.addAttribute("playerCounts", playerCounts);
        model.addAttribute("teams", teams);
        return "Teams";
    }

    @GetMapping("/add-team")
    public String newTeamForm(Model model) {
        model.addAttribute("team", new Team());
        return "AddTeam";
    }

    @PostMapping("/add-team")
    public String addTeam(@ModelAttribute Team team) {
        teamRepository.save(team);
        return "redirect:/teams";
    }

    @GetMapping("/view-roster")
    public String viewRoster(@RequestParam Integer teamId, Model model) {
        Team team = teamRepository.findById(teamId).orElse(null);
        model.addAttribute("team", team);
        assert team != null;
        model.addAttribute("roster", team.getPlayers());
        return "Roster";

    }
}
