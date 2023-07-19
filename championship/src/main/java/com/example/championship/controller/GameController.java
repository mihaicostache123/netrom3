package com.example.championship.controller;

import com.example.championship.model.Game;
import com.example.championship.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping("/all")
    public List<Game> getAllGames() {
        return this.gameService.getAllGames();
    }

    @GetMapping("/sort/date/asc")
    public List<Game> sortGamesByDateAsc() {
        return this.gameService.sortGamesByDateAsc();
    }

    @GetMapping("/sort/date/desc")
    public List<Game> sortGamesByDateDesc() {
        return this.gameService.sortGamesByDateDesc();
    }

    @GetMapping("/id={id}")
    public Game getGameById(@PathVariable("id") int id) {
        return this.gameService.getGameById(id);
    }

    @PostMapping("/add")
    public void addGame(@RequestBody Game game) {
        this.gameService.addGame(game);
    }

    @PutMapping("/update/{id}")
    public void updateGame(@RequestBody Game game) {
        this.gameService.updateGame(game);
    }

    @DeleteMapping("/delete")
    public void deleteGame(@RequestBody Game game) {
        this.gameService.deleteGame(game);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteGameById(@PathVariable("id") int id) {
        this.gameService.deleteGameById(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllGames() {
        this.gameService.deleteAllGames();
    }
}
