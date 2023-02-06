package com.holycode.playerservice.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.holycode.playerservice.PlayerServiceApplicationTests;
import com.holycode.playerservice.domain.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(PlayerController.class)
public class PlayerControllerTest extends PlayerServiceApplicationTests {

    @Autowired
    // IntelliJ IDEA 2021.2.3 pravi problem pa moram suppress
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private MockMvc mvc;

    @MockBean
    private PlayerController playerController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    // Improvement: nisam siguran da li je ovo okej način za testiranje rest-a, vrv. ima neki bolji način za koji nisam čuo
    @Test
    public void testGetPlayerInfo() throws Exception {
        Player player = new Player(1L, "Dejan", 1L);

        ResultActions result = mvc.perform(MockMvcRequestBuilders.get("/player")
                .param("playerId", "1")
                .content(objectMapper.writeValueAsString(player))
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
    }

    @Test
    public void testRegisterPlayer() throws Exception {
        Player player = new Player(1L, "Dejan", 1L);

        ResultActions result = mvc.perform(post("/register")
                .content(objectMapper.writeValueAsString(player))
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
    }

    @Test
    public void testDeletePlayer() throws Exception {
        Player player = new Player(1L, "Dejan", 1L);

        ResultActions result = mvc.perform(delete("/player")
                .param("playerId", "1")
                .content(objectMapper.writeValueAsString(player))
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
    }

}