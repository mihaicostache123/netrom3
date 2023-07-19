package com.example.championship.service;

import com.example.championship.model.Game;
import com.example.championship.model.Team;
import com.example.championship.repository.GameRepository;
import com.example.championship.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private TeamRepository teamRepository;

    public List<Game> getAllGames(){
        return gameRepository.findAll();
    }

    public void updateGame(Game game) {
        if(gameRepository.findById(game.getId()).isPresent()){
            gameRepository.save(game);
        }
        else {
            throw new Error("Game with id " + game.getId() + " does not exist");
        }
    }
    public void deleteGame(Game game) {
            gameRepository.delete(game);
    }

    public void deleteAllGames() {
        this.gameRepository.deleteAll();
    }

    public Game getGameById(int id) {
        return (Game)this.gameRepository.findById(id).get();
    }

    public List<Game> sortGamesByDateAsc() {
        return gameRepository.findAll(Sort.by(Sort.Direction.ASC, "date"));
    }

    public List<Game> sortGamesByDateDesc() {
        return gameRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }

    public Game addGame(Game newGame) {

        Team dbTeamHome = teamRepository.findById(newGame.getTeam1().getId()).get();
        if (dbTeamHome.getTotalScoreHome() == null) {
            dbTeamHome.updateScore(newGame.getScore1());
        }

        Team dbTeamAway = teamRepository.findById(newGame.getTeam2().getId()).get();
        if (dbTeamAway.getTotalScoreAway() == null) {
            dbTeamAway.updateScore(newGame.getScore2());
        }

        teamRepository.save(dbTeamHome);
        newGame.setTeam1(dbTeamHome);

        teamRepository.save(dbTeamAway);
        newGame.setTeam2(dbTeamAway);

        return gameRepository.save(newGame);
    }

    public void deleteGameById(int id) {

        Team dbTeamHome = gameRepository.getReferenceById(id).getTeam1();
        dbTeamHome.setTotalScoreHome(dbTeamHome.getTotalScoreHome() - gameRepository.getReferenceById(id).getScore1());
        dbTeamHome.setTotalScore(dbTeamHome.getTotalScoreHome() + dbTeamHome.getTotalScoreAway());
        teamRepository.save(dbTeamHome);
        gameRepository.getReferenceById(id).setTeam1(dbTeamHome);

        Team dbTeamAway = gameRepository.getReferenceById(id).getTeam2();
        dbTeamAway.setTotalScoreAway(dbTeamAway.getTotalScoreAway() - gameRepository.getReferenceById(id).getScore2());
        dbTeamAway.setTotalScore(dbTeamAway.getTotalScoreHome() + dbTeamAway.getTotalScoreAway());
        teamRepository.save(dbTeamAway);
        gameRepository.getReferenceById(id).setTeam2(dbTeamAway);

        gameRepository.deleteById(id);
    }

}
