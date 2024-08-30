package cs3220.hw2.model;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String gender;
    private int birthYear;
    @Column(name = "team_id")
    private Integer teamId;

    @ManyToOne
    @JoinColumn(name = "team_id", insertable = false, updatable = false)
    private Team team;

    // No-argument constructor
    public Player() {
    }

    public Player(Integer id, String name, String gender, int birthYear, Integer teamId){
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthYear = birthYear;
        this.teamId = teamId;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId(){
        return id;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
    public int getBirthYear() {
        return birthYear;
    }

    public Integer getTeamId() {
        return teamId;
    }
    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }
    public int getAge(){
        return java.time.Year.now().getValue() - birthYear;
    }
}
