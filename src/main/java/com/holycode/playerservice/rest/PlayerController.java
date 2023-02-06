package com.holycode.playerservice.rest;

import com.holycode.playerservice.domain.Player;
import com.holycode.playerservice.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerController {

    private PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    // Da li nam je dovoljno dobro dohvatanje igre preko @RequestParam?
    // Može da se uradi i preko @RequestHeader
    @GetMapping("/player")
    public ResponseEntity<Player> getPlayerInfo(@RequestParam Long playerId) {
        Player player = playerService.getInfo(playerId);

        if (player == null) {
            return ResponseEntity.ok(new Player());
        }

        return ResponseEntity.ok(player);
    }

    @GetMapping(value = "/all")
    public List<Player> getAllPlayers() {
        List<Player> players = playerService.findAll();
        return players;
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registerPlayer(@RequestBody Player newPlayer) {
        Player player = playerService.register(newPlayer);
        return new ResponseEntity<>("Player registered", HttpStatus.CREATED);
    }

    @DeleteMapping("/player")
    public ResponseEntity<String> deletePlayer(@RequestParam Long playerId) {
        playerService.delete(playerId);
        return ResponseEntity.ok("Player deleted");
    }

}
