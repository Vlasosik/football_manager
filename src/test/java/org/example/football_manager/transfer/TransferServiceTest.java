package org.example.football_manager.transfer;

import org.example.football_manager.command.Command;
import org.example.football_manager.command.CommandBalanceUpdater;
import org.example.football_manager.command.CommandService;
import org.example.football_manager.command.dto.CommandMapper;
import org.example.football_manager.player.Player;
import org.example.football_manager.player.PlayerCommandUpdater;
import org.example.football_manager.player.PlayerService;
import org.example.football_manager.player.dto.PlayerMapper;
import org.example.football_manager.transfer.dto.TransferDTO;
import org.example.football_manager.transfer.dto.TransferMapper;
import org.example.football_manager.transfer.exception.NotTransferExistByIdException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransferServiceTest {
    @Mock
    private TransferRepository transferRepository;
    @Mock
    private CommandService commandService;
    @Mock
    private PlayerService playerService;
    @Mock
    private PlayerCommandUpdater playerCommandUpdater;
    @Mock
    private CommandBalanceUpdater commandBalanceUpdater;
    @Mock
    private TransferInvoicesProcessor transferInvoicesProcessor;
    @InjectMocks
    private TransferService transferService;

    private Command fromCommand;
    private Command toCommand;
    private Player player;
    private Transfer transfer;

    @BeforeEach
    void setUp() {
        fromCommand = new Command(
                UUID.fromString("9de5d04c-7714-494c-8518-90fc7bec1e3d"),
                "Malorka",
                BigDecimal.valueOf(1000000.00),
                BigDecimal.valueOf(1.50)
        );
        toCommand = new Command(
                UUID.fromString("7471cc38-83f8-4f2a-97ac-62b4ff3137d5"),
                "Betis",
                BigDecimal.valueOf(1052000.00),
                BigDecimal.valueOf(2.50)
        );
        player = new Player(
                UUID.fromString("89a096fc-439d-4482-97d0-bfd8c4ce61fe"),
                fromCommand.getId(),
                "Fredy",
                "McDonald",
                (short) 18,
                (short) 0
        );
        transfer = new Transfer(
                UUID.fromString("f5bf87eb-d0d5-420e-82f8-926c2bb4b9e3"),
                player.getId(),
                fromCommand.getId(),
                toCommand.getId(),
                BigDecimal.valueOf(1000.00),
                BigDecimal.valueOf(8.00),
                BigDecimal.valueOf(10000.00),
                LocalDateTime.now()
        );
    }

    @Test
    void saveTransferTest() {
        when(commandService.readById(fromCommand.getId())).thenReturn(CommandMapper.toDto(fromCommand));
        when(commandService.readById(toCommand.getId())).thenReturn(CommandMapper.toDto(toCommand));
        when(playerService.readById(player.getId())).thenReturn(PlayerMapper.toDto(player));
        when(transferInvoicesProcessor.getTotalAmountOfTransfer(any(), any())).thenReturn(BigDecimal.valueOf(10000.00));
        when(transferInvoicesProcessor.getCostOfTransfer(any())).thenReturn(BigDecimal.valueOf(1000.00));
        when(transferRepository.save(any(Transfer.class))).thenReturn(transfer);

        TransferDTO transferDTO = TransferMapper.toDto(transfer);
        transferService.create(transferDTO);
        verify(transferRepository, times(1)).save(any(Transfer.class));
        assertNotNull(transferDTO);
        assertEquals(transfer.getId(), transferDTO.getId());
    }

    @Test
    void readTransferByIdTest() {
        when(transferRepository.findById(transfer.getId())).thenReturn(Optional.of(transfer));
        TransferDTO transferDTO = transferService.readById(transfer.getId());
        verify(transferRepository, times(1)).findById(transfer.getId());
        assertNotNull(transfer);
        assertEquals(transferDTO.getId(), transfer.getId());
    }

    @Test
    void updateTransferTest() {
        when(transferRepository.findById(transfer.getId())).thenReturn(Optional.of(transfer));
        transferService.update(TransferMapper.toDto(transfer));
        verify(transferRepository, times(1)).findById(transfer.getId());
        verify(transferRepository, times(1)).save(any(Transfer.class));
    }

    @Test
    void deleteTransferByIdTest() {
        when(transferRepository.findById(transfer.getId())).thenReturn(Optional.of(transfer));
        transferService.deleteById(transfer.getId());
        verify(transferRepository, times(1)).delete(transfer);
    }

    @Test
    void readTransferByIdNotFoundTest() {
        UUID invalidId = UUID.randomUUID();
        when(transferRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> transferService.readById(invalidId))
                .isInstanceOf(NotTransferExistByIdException.class)
                .hasMessage("No such identifier was found in the transfer!");

        verify(transferRepository, times(1)).findById(invalidId);
    }
}
