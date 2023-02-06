package com.holycode.playerservice.service;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.*;

import com.holycode.playerservice.PlayerServiceApplicationTests;
import com.holycode.playerservice.domain.Player;
import com.holycode.playerservice.repository.PlayerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PlayerServiceTest extends PlayerServiceApplicationTests {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private PlayerRepository playerRepository;

    // Podatke popunjavam ovako ali i preko ApplicationRunner u main klasi
    @BeforeEach
    public void setUp() {
        playerRepository.save(new Player(1L, "Dejan", 1L));
    }

    @AfterEach
    public void deleteAll() {
        playerRepository.deleteAll();
    }

    @Test
    public void testGetInfo() {
        final Player player = playerService.getInfo(1L);

        assertThat(player.getId(), is(1L));
        assertThat(player.getName(), is("Dejan"));
        assertThat(player.getGameId(), is(1L));
    }

    @Test
    public void testRegister() {
        final Player player = playerService.register(new Player(6L, "Marko", 2L));

        assertThat(player.getId(), is(6L));
        assertThat(player.getName(), is("Marko"));
        assertThat(player.getGameId(), is(2L));
    }

}
