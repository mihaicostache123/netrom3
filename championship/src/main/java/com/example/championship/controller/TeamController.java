package com.example.championship.controller;

import com.example.championship.model.Game;
import com.example.championship.model.Player;
import com.example.championship.model.Team;
import com.example.championship.service.PlayerService;
import com.example.championship.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @GetMapping("/all")
    public List<Team> getAllTeams() {
        return this.teamService.getAllTeams();
    }

    @GetMapping("/id={id}")
    public Team getTeamById(@PathVariable("id") int id) {
        return this.teamService.getTeamById(id);
    }

    @GetMapping("/sort/name/asc")
    public List<Team> sortTeamsByNameAsc() {
        return teamService.sortTeamsByNameAsc();
    }

    @GetMapping("/sort/name/desc")
    public List<Team> sortTeamsByNameDesc() {
        return teamService.sortTeamsByNameDesc();
    }

    @GetMapping("/sort/country/asc")
    public List<Team> sortTeamsByCountryAsc() {
        return teamService.sortTeamsByCountryAsc();
    }

    @GetMapping("/sort/country/desc")
    public List<Team> sortTeamsByCountryDesc() {
        return teamService.sortTeamsByCountryDesc();
    }

    @PostMapping("/add")
    public void addTeam(@RequestBody Team team) {
        this.teamService.addTeam(team);
    }

    @PutMapping("/update/{id}")
    public void updateTeam(@RequestBody Team team) {
        this.teamService.updateTeam(team);
    }

    @DeleteMapping("/delete")
    public void deleteTeam(@RequestBody Team team) {
        this.teamService.deleteTeam(team);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTeamById(@PathVariable("id") Long id) {
        this.teamService.deleteTeamById(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllTeams() {
        this.teamService.deleteAllTeams();
    }
}