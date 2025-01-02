package org.example.football_manager.player;

import lombok.RequiredArgsConstructor;
import org.example.football_manager.command.exception.NotCommandExistById;
import org.example.football_manager.player.dto.PlayerDTO;
import org.example.football_manager.player.dto.PlayerMapper;
import org.example.football_manager.player.exception.NotPlayerExistById;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;

    public void create(PlayerDTO playerDTO) {
        playerRepository.save(PlayerMapper.toEntity(playerDTO));
    }

    public Player readById(UUID id) {
        return playerRepository.findById(id).orElseThrow(NotPlayerExistById::new);
    }

    public void update(PlayerDTO playerDTO) {
        playerRepository.findById(playerDTO.getId())
                .orElseThrow(NotCommandExistById::new);
        playerRepository.save(PlayerMapper.toEntity(playerDTO));
    }

    public void deleteById(UUID id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(NotPlayerExistById::new);
        playerRepository.delete(player);
    }
}
