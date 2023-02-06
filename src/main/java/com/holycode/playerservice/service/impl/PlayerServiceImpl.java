package com.holycode.playerservice.service.impl;

import com.google.common.collect.Lists;
import com.holycode.playerservice.domain.Player;
import com.holycode.playerservice.repository.PlayerRepository;
import com.holycode.playerservice.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PlayerServiceImpl implements PlayerService {

    private PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    // Improvement: umesto logovanja preko Slf4j, zgodno je i AOP da se koristi.
    @Override
    public Player getInfo(Long playerId) {
        log.info("Pozvana getInfo(Long playerId) metoda.");
        Player player = null;

        try {
            player = playerRepository.findById(playerId).get();
        } catch (Exception e) {
            log.error("Greška prilikom dohvatanja igrača.");
        }

        log.info("Metoda getInfo(Long playerId) uspešno izvršena.");
        return player;
    }

    @Override
    public Player register(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public void delete(Long playerId) {
        playerRepository.deleteById(playerId);
    }

    @Override
    public List<Player> findAll() {
        return Lists.newArrayList(playerRepository.findAll().iterator());
    }
}
