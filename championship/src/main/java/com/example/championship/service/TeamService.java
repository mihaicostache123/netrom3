package com.example.championship.service;

import com.example.championship.model.Game;
import com.example.championship.model.Player;
import com.example.championship.model.Team;
import com.example.championship.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;

    public List<Team> getAllTeams() {
        return this.teamRepository.findAll();
    }

    public void addTeam(Team team) {
        this.teamRepository.save(team);
    }

    public void updateTeam(Team team) {
        this.teamRepository.save(team);
    }

    public void deleteTeam(Team team) {
        this.teamRepository.delete(team);
    }

    public void deleteTeamById(Long id) {
        this.teamRepository.deleteById(Math.toIntExact(id));
    }

    public void deleteAllTeams() {
        this.teamRepository.deleteAll();
    }

    public Team getTeamById(int id) {
        return (Team)this.teamRepository.findById(id).get();
    }

    public List<Team> sortTeamsByNameAsc() {
        return teamRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public List<Team> sortTeamsByNameDesc() {
        return teamRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
    }

    public List<Team> sortTeamsByCountryAsc() {
        return teamRepository.findAll(Sort.by(Sort.Direction.ASC, "location"));
    }

    public List<Team> sortTeamsByCountryDesc() {
        return teamRepository.findAll(Sort.by(Sort.Direction.DESC, "location"));
    }

}
