package org.example.football_manager.transfer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.football_manager.command.dto.CommandDTO;
import org.example.football_manager.player.dto.PlayerDTO;
import org.example.football_manager.transfer.dto.TransferDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransferController.class)
public class TransferControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockitoBean
    private TransferService transferService;

    private TransferDTO transferDTO;

    @BeforeEach
    void setUp() {
        CommandDTO fromCommandDTO = new CommandDTO(
                UUID.fromString("9de5d04c-7714-494c-8518-90fc7bec1e3d"),
                "Malorka",
                BigDecimal.valueOf(1000000.00),
                BigDecimal.valueOf(1.50)
        );
        CommandDTO toCommandDTO = new CommandDTO(
                UUID.fromString("7471cc38-83f8-4f2a-97ac-62b4ff3137d5"),
                "Betis",
                BigDecimal.valueOf(1052000.00),
                BigDecimal.valueOf(2.50)
        );
        PlayerDTO playerDTO = new PlayerDTO(
                UUID.fromString("89a096fc-439d-4482-97d0-bfd8c4ce61fe"),
                fromCommandDTO.getId(),
                "Fredy",
                "McDonald",
                (short) 18,
                (short) 0
        );
        transferDTO = new TransferDTO(
                UUID.fromString("f5bf87eb-d0d5-420e-82f8-926c2bb4b9e3"),
                playerDTO.getId(),
                fromCommandDTO.getId(),
                toCommandDTO.getId(),
                BigDecimal.valueOf(1000.00),
                BigDecimal.valueOf(8.00),
                BigDecimal.valueOf(10000.00),
                LocalDateTime.now()
        );
    }

    @Test
    void createTransferTest() throws Exception {
        doNothing().when(transferService).create(any(TransferDTO.class));

        mockMvc.perform(post("/api/v1/transfers/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transferDTO)))
                .andExpect(status().isCreated());
        verify(transferService, times(1)).create(any(TransferDTO.class));
    }

    @Test
    void readTransferByIdTest() throws Exception {
        when(transferService.readById(transferDTO.getId())).thenReturn(transferDTO);

        mockMvc.perform(get("/api/v1/transfers").param("id", transferDTO.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(transferDTO.getId().toString()))
                .andExpect(jsonPath("$.playerId").value(transferDTO.getPlayerId().toString()))
                .andExpect(jsonPath("$.fromCommandId").value(transferDTO.getFromCommandId().toString()))
                .andExpect(jsonPath("$.toCommandId").value(transferDTO.getToCommandId().toString()))
                .andExpect(jsonPath("$.salesCommissionFee").value(String.valueOf(transferDTO.getSalesCommissionFee())))
                .andExpect(jsonPath("$.totalAmount").value(String.valueOf(transferDTO.getTotalAmount())))
                .andExpect(jsonPath("$.transferDate").value(String.valueOf(transferDTO.getTransferDate())));
    }
    @Test
    void updatePlayerTest() throws Exception {
        doNothing().when(transferService).update(any(TransferDTO.class));
        mockMvc.perform(put("/api/v1/transfers/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transferDTO)))
                .andExpect(jsonPath("$.message").value("Transfer successfully update!"))
                .andExpect(status().isCreated());
        verify(transferService, times(1)).update(any(TransferDTO.class));
    }
    @Test
    void deleteCommandByIdTest() throws Exception {
        doNothing().when(transferService).deleteById(transferDTO.getId());
        mockMvc.perform(delete("/api/v1/transfers/delete").param("id", transferDTO.getId().toString()))
                .andExpect(jsonPath("$.message").value("Transfer successfully deleted!"))
                .andExpect(status().isOk());
    }
}
