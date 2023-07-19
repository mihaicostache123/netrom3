package com.example.championship.repository;

import com.example.championship.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    List<Player> findAllByOrderByIdAsc();

    List<Player> findAllByOrderByNameAsc();

    List<Player> findAllByOrderByAgeAsc();

    List<Player> findAllByOrderByPositionAsc();

    List<Player> findAllByOrderByNumberAsc();
}
