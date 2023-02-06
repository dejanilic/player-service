package com.holycode.playerservice.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.*;

import com.holycode.playerservice.PlayerServiceApplicationTests;
import com.holycode.playerservice.domain.Player;
import com.holycode.playerservice.repository.PlayerRepository;
import com.holycode.playerservice.service.impl.PlayerServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceMockTest extends PlayerServiceApplicationTests {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerServiceImpl playerService;

    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player(1L, "Dejan", 1L);
    }

    @AfterEach
    public void deleteAll() {
        playerRepository.deleteAll();
    }

    // Simuliramo slučaj kada baza nije dostupna
    @Test
    public void testGetInfo() {
        given(playerRepository.findById(any())).willReturn(Optional.of(player));

        final Player player = playerService.getInfo(1L);

        assertThat(player.getId(), is(1L));
        assertThat(player.getName(), is("Dejan"));
        assertThat(player.getGameId(), is(1L));
    }

}
