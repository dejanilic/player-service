package com.holycode.playerservice.service;

import com.holycode.playerservice.domain.Player;

import java.util.List;

public interface PlayerService {

    /**
     * Vraća info o igraču na osnovu id-a.
     *
     * @param playerId - ID igrača
     */
    Player getInfo(Long playerId);

    /**
     * Kreira i upisuje novog igrača u bazu.
     *
     * @param player - igrač koji se upisuje
     */
    Player register(Player player);

    /**
     * Briše igrača iz baze.
     *
     * @param playerId - ID igrača
     */
    void delete(Long playerId);

    /**
     * Vraća sve igrače iz baze.
     *
     */
    List<Player> findAll();
}
