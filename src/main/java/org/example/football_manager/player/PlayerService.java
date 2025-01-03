package org.example.football_manager.player;

import lombok.RequiredArgsConstructor;
import org.example.football_manager.command.CommandService;
import org.example.football_manager.command.dto.CommandDTO;
import org.example.football_manager.command.exception.NotCommandExistByIdException;
import org.example.football_manager.player.dto.PlayerDTO;
import org.example.football_manager.player.dto.PlayerMapper;
import org.example.football_manager.player.exception.NotPlayerExistByIdException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final CommandService commandService;

    public void create(PlayerDTO playerDTO) {
        checkSuchTeamExistById(playerDTO);
        playerRepository.save(PlayerMapper.toEntity(playerDTO));
    }

    public PlayerDTO readById(UUID id) {
        return PlayerMapper.toDto(playerRepository.findById(id).orElseThrow(NotPlayerExistByIdException::new));
    }

    public void update(PlayerDTO playerDTO) {
        checkSuchTeamExistById(playerDTO);
        playerRepository.findById(playerDTO.getId())
                .orElseThrow(NotCommandExistByIdException::new);
        playerRepository.save(PlayerMapper.toEntity(playerDTO));
    }

    public void deleteById(UUID id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(NotPlayerExistByIdException::new);
        playerRepository.delete(player);
    }

    private void checkSuchTeamExistById(PlayerDTO playerDTO) {
        CommandDTO commandDto = commandService.readById(playerDTO.getCommandId());
        if (commandDto == null) {
            throw new NotCommandExistByIdException();
        }
    }
}
