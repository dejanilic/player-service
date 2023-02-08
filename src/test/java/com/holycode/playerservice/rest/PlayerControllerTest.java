package com.holycode.playerservice.rest;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.holycode.playerservice.PlayerServiceApplicationTests;
import com.holycode.playerservice.domain.Player;
import com.holycode.playerservice.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(PlayerController.class)
public class PlayerControllerTest extends PlayerServiceApplicationTests {

    @Autowired
    // IntelliJ IDEA 2021.2.3 pravi problem pa moram suppress
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private MockMvc mvc;

    @MockBean
    private PlayerService playerService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGetPlayerInfo() throws Exception {
        given(playerService.getInfo(any())).willReturn(new Player(1L, "Dejan", 10L));

        mvc.perform(MockMvcRequestBuilders.get("/player")
                        .param("playerId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("Dejan")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gameId", is(10)));
    }

    @Test
    public void testRegisterPlayer() throws Exception {
        Player player = new Player(1L, "Dejan", 10L);

        given(playerService.register(any())).willReturn(player);

        mvc.perform(MockMvcRequestBuilders.post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(player)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testDeletePlayer() throws Exception {

        mvc.perform(MockMvcRequestBuilders.delete("/player")
                        .param("playerId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}