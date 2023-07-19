package com.example.championship.service;

import com.example.championship.model.Player;
import com.example.championship.repository.PlayerRepository;
import com.example.championship.repository.TeamRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private TeamRepository teamRepository;

    public PlayerService() {
    }

    public List<Player> getAllPlayers() {
        return this.playerRepository.findAll();
    }

    public void addPlayer(Player player) {
        if (this.teamRepository.findById(player.getTeam().getId()).isPresent()) {
            this.playerRepository.save(player);
        } else {
            throw new Error("Team with id " + player.getTeam().getId() + " does not exist");
        }
    }

    public void updatePlayer(Player player) {
        if (this.teamRepository.findById(player.getTeam().getId()).isPresent()) {
            this.playerRepository.save(player);
        } else {
            throw new Error("Team with id " + player.getTeam().getId() + " does not exist");
        }
    }

    public void deletePlayer(Player player) {
        this.playerRepository.delete(player);
    }

    public void deletePlayerById(Long id) {
        this.playerRepository.deleteById(Math.toIntExact(id));
    }

    public void deleteAllPlayers() {
        this.playerRepository.deleteAll();
    }

    public Player getPlayerById(int id) {
        return (Player)this.playerRepository.findById(id).get();
    }

    public List<Player> sortPlayersByNameAsc() {
        return playerRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public List<Player> sortPlayersByNameDesc() {
        return playerRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
    }

    public List<Player> sortPlayersByAgeAsc() {
        return playerRepository.findAll(Sort.by(Sort.Direction.ASC, "age"));
    }

    public List<Player> sortPlayersByAgeDesc() {
        return playerRepository.findAll(Sort.by(Sort.Direction.DESC, "age"));
    }

    public List<Player> sortPlayersByNumberAsc() {
        return playerRepository.findAll(Sort.by(Sort.Direction.ASC, "number"));
    }

    public List<Player> sortPlayersByNumberDesc() {
        return playerRepository.findAll(Sort.by(Sort.Direction.DESC, "number"));
    }

    public List<Player> sortPlayersByPositionAsc(){
        return playerRepository.findAll(Sort.by(Sort.Direction.ASC, "position"));
    }

    public List<Player> sortPlayersByPositionDesc() {
        return playerRepository.findAll(Sort.by(Sort.Direction.DESC, "position"));
    }
}
