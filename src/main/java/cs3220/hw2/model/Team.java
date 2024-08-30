package cs3220.hw2.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String uniformColor;
    private String gender;
    private int minAge;
    private int maxAge;
    @OneToMany(mappedBy = "teamId", cascade = CascadeType.ALL)
    private List<Player> players;

    // Constructor, getters, and setters
    public Team(Integer id, String uniformColor, String gender, int minAge, int maxAge) {
        this.id = id;
        this.uniformColor = uniformColor;
        this.gender = gender;
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    public Team() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUniformColor() {
        return uniformColor;
    }

    public void setUniformColor(String uniformColor) {
        this.uniformColor = uniformColor;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
