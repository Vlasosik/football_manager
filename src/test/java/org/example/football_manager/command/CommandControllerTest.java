package org.example.football_manager.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.football_manager.command.dto.CommandDTO;
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

@WebMvcTest(CommandController.class)
public class CommandControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockitoBean
    private CommandService commandService;

    private CommandDTO commandDTO;

    @BeforeEach
    void setUp() {
        commandDTO = new CommandDTO();
        commandDTO.setId(UUID.fromString("9de5d04c-7714-494c-8518-90fc7bec1e3d"));
        commandDTO.setName("Malorka");
        commandDTO.setBalance(BigDecimal.valueOf(1000000.00));
        commandDTO.setCommissionRate(BigDecimal.valueOf(1.50));
    }

    @Test
    void createCommandTest() throws Exception {
        doNothing().when(commandService).create(any(CommandDTO.class));
        mockMvc.perform(post("/api/v1/commands/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commandDTO)))
                .andExpect(status().isCreated());
        verify(commandService, times(1)).create(any(CommandDTO.class));
    }

    @Test
    void readCommandByIdTest() throws Exception {
        when(commandService.readById(commandDTO.getId())).thenReturn(commandDTO);

        mockMvc.perform(get("/api/v1/commands").param("id", commandDTO.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(commandDTO.getId().toString()))
                .andExpect(jsonPath("$.name").value(commandDTO.getName()))
                .andExpect(jsonPath("$.balance").value(commandDTO.getBalance().toString()))
                .andExpect(jsonPath("$.commissionRate").value(commandDTO.getCommissionRate().toString()));
    }

    @Test
    void updateCommandTest() throws Exception {
        doNothing().when(commandService).update(any(CommandDTO.class));
        mockMvc.perform(put("/api/v1/commands/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commandDTO)))
                .andExpect(jsonPath("$.message").value("Command successfully update!"))
                .andExpect(status().isCreated());
        verify(commandService, times(1)).update(any(CommandDTO.class));
    }

    @Test
    void deleteCommandByIdTest() throws Exception {
        doNothing().when(commandService).deleteById(commandDTO.getId());
        mockMvc.perform(delete("/api/v1/commands/delete").param("id", commandDTO.getId().toString()))
                .andExpect(jsonPath("$.message").value("Command successfully deleted!"))
                .andExpect(status().isOk());
    }

}
