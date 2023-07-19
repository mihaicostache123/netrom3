package com.example.championship.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "TEAM_NAME", nullable = false)
    //@NotBlank(message = "Team name is mandatory")
    private String name;

    @Column(name = "COUNTRY", nullable = false)
    ///@NotBlank(message = "Country is mandatory")
    private String country;

    @OneToMany(mappedBy = "team")
    @JsonIgnore
    private List<Player> players = new ArrayList<>();

    @OneToMany(mappedBy = "team1")
    @JsonIgnore
    private List<Game> gamesHome = new ArrayList<>();

    @OneToMany(mappedBy = "team2")
    @JsonIgnore
    private List<Game> gamesAway = new ArrayList<>();

    @Column(name = "total_score")
    private Integer totalScore = 0;

    @Column(name = "total_score_home")
    private Integer totalScoreHome = 0;

    @Column(name = "total_score_away")
    private Integer totalScoreAway = 0;

    public int updateScoreHome(Integer score){
        if(this.totalScoreHome == null)
            this.totalScoreHome = 0;
        this.totalScoreHome += score;
        return this.totalScoreHome;
    }

    public int updateScoreAway(Integer score){
        if(this.totalScoreAway == null)
            this.totalScoreAway = 0;
        this.totalScoreAway += score;
        return this.totalScoreAway;
    }

    public int updateScore(Integer score){
        if(this.totalScore == null)
            this.totalScore = 0;
        this.totalScore += score;
        return this.totalScore;
    }

}