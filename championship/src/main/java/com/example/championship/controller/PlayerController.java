package com.example.championship.controller;

import com.example.championship.model.Player;
import com.example.championship.service.PlayerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    private PlayerService playerservice;

    @GetMapping("/all")
    public List<Player> getAllPlayers() {
        return this.playerservice.getAllPlayers();
    }

    @GetMapping("/sort/name/asc")
    public List<Player> sortPlayersByNameAsc() {
        return this.playerservice.sortPlayersByNameAsc();
    }

    @GetMapping("/sort/name/desc")
    public List<Player> sortPlayersByNameDesc() {
        return this.playerservice.sortPlayersByNameDesc();
    }

    @GetMapping("/sort/age/asc")
    public List<Player> sortPlayersByAgeAsc() {
        return this.playerservice.sortPlayersByAgeAsc();
    }

    @GetMapping("/sort/age/desc")
    public List<Player> sortPlayersByAgeDesc() {
        return this.playerservice.sortPlayersByAgeDesc();
    }

    @GetMapping("/sort/position/asc")
    public List<Player> sortPlayersByPositionAsc() {
        return this.playerservice.sortPlayersByPositionAsc();
    }

    @GetMapping("/sort/position/desc")
    public List<Player> sortPlayersByPositionDesc() {
        return this.playerservice.sortPlayersByPositionDesc();
    }

    @GetMapping("/sort/number/asc")
    public List<Player> sortPlayersByNumberAsc() {
        return this.playerservice.sortPlayersByNumberAsc();
    }

    @GetMapping("/sort/number/desc")
    public List<Player> sortPlayersByNumberDesc() {
        return this.playerservice.sortPlayersByNumberDesc();
    }

    @GetMapping("/id={id}")
    public Player getPlayerById(@PathVariable("id") int id) {
        return this.playerservice.getPlayerById(id);
    }

    @PostMapping("/add")
    public void addPlayer(@RequestBody Player player) {
        this.playerservice.addPlayer(player);
    }

    @PutMapping("/update/{id}")
    public void updatePlayer(@RequestBody Player player) {
        this.playerservice.updatePlayer(player);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePlayerById(@PathVariable("id") Long id) {
        this.playerservice.deletePlayerById(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllPlayers() {
        this.playerservice.deleteAllPlayers();
    }
}
