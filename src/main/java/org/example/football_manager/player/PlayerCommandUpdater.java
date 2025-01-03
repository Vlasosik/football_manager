package org.example.football_manager.player;

import lombok.RequiredArgsConstructor;
import org.example.football_manager.command.dto.CommandDTO;
import org.example.football_manager.player.dto.PlayerDTO;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlayerCommandUpdater {
    private final PlayerService playerService;

    public void updatePlayerInCommand(PlayerDTO playerDTO, CommandDTO toCommand) {
        playerDTO.setCommandId(toCommand.getId());
        playerService.update(playerDTO);
    }
}
