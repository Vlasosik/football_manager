package org.example.football_manager.player.dto;

import org.example.football_manager.player.Player;

public class PlayerMapper {
    public static PlayerDTO toDto(Player player) {
        return new PlayerDTO(
                player.getId(),
                player.getCommandId(),
                player.getFirstName(),
                player.getLastName(),
                player.getAge(),
                player.getExperience()
        );
    }

    public static Player toEntity(PlayerDTO playerDTO) {
        return new Player(
                playerDTO.getId(),
                playerDTO.getCommandId(),
                playerDTO.getFirstName(),
                playerDTO.getLastName(),
                playerDTO.getAge(),
                playerDTO.getExperience()
        );
    }
}
