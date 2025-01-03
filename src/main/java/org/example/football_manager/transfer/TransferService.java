package org.example.football_manager.transfer;

import lombok.RequiredArgsConstructor;
import org.example.football_manager.command.CommandBalanceUpdater;
import org.example.football_manager.command.CommandService;
import org.example.football_manager.command.dto.CommandDTO;
import org.example.football_manager.command.exception.NotCommandExistByIdException;
import org.example.football_manager.player.PlayerCommandUpdater;
import org.example.football_manager.player.PlayerService;
import org.example.football_manager.player.dto.PlayerDTO;
import org.example.football_manager.transfer.dto.TransferDTO;
import org.example.football_manager.transfer.dto.TransferMapper;
import org.example.football_manager.transfer.exception.InsufficientBalanceException;
import org.example.football_manager.transfer.exception.NotTransferExistByIdException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransferService {
    private final TransferRepository transferRepository;
    private final TransferInvoicesProcessor transferInvoicesProcessor;
    private final CommandService commandService;
    private final CommandBalanceUpdater commandBalanceUpdater;
    private final PlayerService playerService;
    private final PlayerCommandUpdater playerCommandUpdater;


    public void create(TransferDTO transferDTO) {
        PlayerDTO playerById = playerService.readById(transferDTO.getPlayerId());
        CommandDTO fromCommandById = commandService.readById(transferDTO.getFromCommandId());
        CommandDTO toCommandById = commandService.readById(transferDTO.getToCommandId());

        BigDecimal costOfTransfer = transferInvoicesProcessor.getCostOfTransfer(playerById);
        BigDecimal totalAmountOfTransfer = transferInvoicesProcessor.
                getTotalAmountOfTransfer(fromCommandById, playerById);

        if (toCommandById.getBalance().compareTo(totalAmountOfTransfer) < 0) {
            throw new InsufficientBalanceException();
        }
        commandBalanceUpdater.updateCommandBalance(
                fromCommandById, toCommandById, totalAmountOfTransfer
        );

        playerCommandUpdater.updatePlayerInCommand(playerById, toCommandById);

        createTransfer(transferDTO, costOfTransfer, fromCommandById, totalAmountOfTransfer);
    }

    public TransferDTO readById(UUID id) {
        return TransferMapper.toDto(transferRepository.findById(id).orElseThrow(NotTransferExistByIdException::new));
    }

    public void update(TransferDTO transferDTO) {
        transferRepository.findById(transferDTO.getId())
                .orElseThrow(NotCommandExistByIdException::new);
        transferRepository.save(TransferMapper.toEntity(transferDTO));
    }

    public void deleteById(UUID id) {
        Transfer transfer = transferRepository.findById(id)
                .orElseThrow(NotTransferExistByIdException::new);
        transferRepository.delete(transfer);
    }

    private void createTransfer(TransferDTO transferDTO, BigDecimal costOfTransfer, CommandDTO fromCommandId, BigDecimal totalAmountOfTransfer) {
        Transfer transfer = new Transfer(
                transferDTO.getId(),
                transferDTO.getPlayerId(),
                transferDTO.getFromCommandId(),
                transferDTO.getToCommandId(),
                costOfTransfer,
                fromCommandId.getCommissionRate(),
                totalAmountOfTransfer,
                transferDTO.getTransferDate()
        );
        transferRepository.save(transfer);
    }
}



