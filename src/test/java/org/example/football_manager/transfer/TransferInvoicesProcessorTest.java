package org.example.football_manager.transfer;

import org.example.football_manager.command.dto.CommandDTO;
import org.example.football_manager.player.dto.PlayerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferInvoicesProcessorTest {
    private TransferInvoicesProcessor transferInvoicesProcessor;
    private PlayerDTO playerDTO;
    private CommandDTO commandDTO;

    @BeforeEach
    void setUp() {
        transferInvoicesProcessor = new TransferInvoicesProcessor();
        commandDTO = new CommandDTO(
                UUID.fromString("9de5d04c-7714-494c-8518-90fc7bec1e3d"),
                "Malorka",
                BigDecimal.valueOf(1000000.00),
                BigDecimal.valueOf(1.50)
        );
        playerDTO = new PlayerDTO(
                UUID.fromString("89a096fc-439d-4482-97d0-bfd8c4ce61fe"),
                commandDTO.getId(),
                "Fredy",
                "McDonald",
                (short) 18,
                (short) 1
        );
    }

    @Test
    void testGetCostOfTransfer() {
        BigDecimal costOfTransfer = transferInvoicesProcessor.getCostOfTransfer(playerDTO);
        BigDecimal expectedCost = BigDecimal.valueOf(66666.67);
        assertEquals(expectedCost, costOfTransfer);
    }

    @Test
    void testGetTotalAmountOfTransfer() {
        BigDecimal expectedTotalAmount = BigDecimal.valueOf(67666.67);
        BigDecimal actualTotalAmount = transferInvoicesProcessor.getTotalAmountOfTransfer(commandDTO, playerDTO);
        assertEquals(expectedTotalAmount, actualTotalAmount);
    }
}
