package org.example.football_manager.command;

import lombok.RequiredArgsConstructor;
import org.example.football_manager.command.dto.CommandDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class CommandBalanceUpdater {
    private final CommandService commandService;

    public void updateCommandBalance(CommandDTO fromCommand, CommandDTO toCommand, BigDecimal balance) {
        BigDecimal addBalance = fromCommand.getBalance().add(balance);
        fromCommand.setBalance(addBalance);
        commandService.update(fromCommand);

        BigDecimal subtractBalance = toCommand.getBalance().subtract(balance);
        toCommand.setBalance(subtractBalance);
        commandService.update(toCommand);
    }
}
