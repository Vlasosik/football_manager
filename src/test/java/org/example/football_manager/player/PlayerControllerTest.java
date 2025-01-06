package org.example.football_manager.player;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.football_manager.command.dto.CommandDTO;
import org.example.football_manager.player.dto.PlayerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlayerController.class)
public class PlayerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockitoBean
    private PlayerService playerService;

    private PlayerDTO playerDTO;

    @BeforeEach
    void setUp() {
        CommandDTO commandDTO = new CommandDTO();
        commandDTO.setId(UUID.fromString("9de5d04c-7714-494c-8518-90fc7bec1e3d"));
        commandDTO.setName("Malorka");
        commandDTO.setBalance(BigDecimal.valueOf(1000000.00));
        commandDTO.setCommissionRate(BigDecimal.valueOf(1.50));

        playerDTO = new PlayerDTO();
        playerDTO.setId(UUID.fromString("459ded1d-047c-43e5-9157-2b40073bf300"));
        playerDTO.setCommandId(commandDTO.getId());
        playerDTO.setFirstName("Fredy");
        playerDTO.setLastName("McDonald");
        playerDTO.setAge((short) 18);
        playerDTO.setExperience((short) 0);
    }


    @Test
    void createPlayerTest() throws Exception {
        doNothing().when(playerService).create(any(PlayerDTO.class));

        mockMvc.perform(post("/api/v1/players/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(playerDTO)))
                .andExpect(status().isCreated());
        verify(playerService, times(1)).create(any(PlayerDTO.class));
    }
    @Test
    void readPlayerByIdTest() throws Exception {
        when(playerService.readById(playerDTO.getId())).thenReturn(playerDTO);

        mockMvc.perform(get("/api/v1/players").param("id", playerDTO.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(playerDTO.getId().toString()))
                .andExpect(jsonPath("$.commandId").value(playerDTO.getCommandId().toString()))
                .andExpect(jsonPath("$.firstName").value(playerDTO.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(playerDTO.getLastName()))
                .andExpect(jsonPath("$.age").value(String.valueOf(playerDTO.getAge())))
                .andExpect(jsonPath("$.experience").value(String.valueOf(playerDTO.getExperience())));

    }
    @Test
    void updatePlayerTest() throws Exception {
        doNothing().when(playerService).update(any(PlayerDTO.class));
        mockMvc.perform(put("/api/v1/players/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(playerDTO)))
                .andExpect(jsonPath("$.message").value("Player successfully update!"))
                .andExpect(status().isCreated());
        verify(playerService, times(1)).update(any(PlayerDTO.class));
    }
    @Test
    void deleteCommandByIdTest() throws Exception {
        doNothing().when(playerService).deleteById(playerDTO.getId());
        mockMvc.perform(delete("/api/v1/players/delete").param("id", playerDTO.getId().toString()))
                .andExpect(jsonPath("$.message").value("Player successfully deleted!"))
                .andExpect(status().isOk());
    }
}
