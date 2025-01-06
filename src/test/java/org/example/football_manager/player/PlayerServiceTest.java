package org.example.football_manager.player;

import org.example.football_manager.command.Command;
import org.example.football_manager.command.CommandService;
import org.example.football_manager.command.dto.CommandMapper;
import org.example.football_manager.player.dto.PlayerDTO;
import org.example.football_manager.player.dto.PlayerMapper;
import org.example.football_manager.player.exception.NotPlayerExistByIdException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {
    @Mock
    private PlayerRepository playerRepository;
    @InjectMocks
    private PlayerService playerService;
    @Mock
    private CommandService commandService;

    private Player player;
    private Command command;

    @BeforeEach
    void setUp() {
        command = new Command();
        command.setId(UUID.fromString("9de5d04c-7714-494c-8518-90fc7bec1e3d"));
        command.setName("Malorka");
        command.setBalance(BigDecimal.valueOf(1000000.00));
        command.setCommissionRate(BigDecimal.valueOf(1.50));

        player = new Player();
        player.setId(UUID.fromString("459ded1d-047c-43e5-9157-2b40073bf300"));
        player.setCommandId(command.getId());
        player.setFirstName("Fredy");
        player.setLastName("McDonald");
        player.setAge((short) 18);
        player.setExperience((short) 0);
    }

    @Test
    void savePlayerTest() {
        when(commandService.readById(command.getId())).thenReturn(CommandMapper.toDto(command));
        when(playerRepository.save(any(Player.class))).thenReturn(player);
        PlayerDTO playerDTO = PlayerMapper.toDto(player);
        playerService.create(playerDTO);
        verify(playerRepository, times(1)).save(any(Player.class));
        assertNotNull(playerDTO);
        assertEquals(player.getId(), playerDTO.getId());
    }

    @Test
    void readPlayerByIdTest() {
        when(playerRepository.findById(player.getId())).thenReturn(Optional.of(player));
        PlayerDTO playerDTO = playerService.readById(player.getId());
        verify(playerRepository, times(1)).findById(playerDTO.getId());
        assertNotNull(playerDTO);
        assertEquals(playerDTO.getId(), player.getId());
    }

    @Test
    void updatePlayerTest() {
        when(commandService.readById(command.getId())).thenReturn(CommandMapper.toDto(command));
        when(playerRepository.findById(player.getId())).thenReturn(Optional.of(player));
        when(playerRepository.save(any(Player.class))).thenReturn(player);

        PlayerDTO playerDTO = PlayerMapper.toDto(player);
        playerService.update(playerDTO);

        verify(playerRepository, times(1)).findById(player.getId());
        verify(playerRepository, times(1)).save(any(Player.class));
    }

    @Test
    void deletePlayerTest() {
        when(playerRepository.findById(player.getId())).thenReturn(Optional.of(player));
        playerService.deleteById(player.getId());
        verify(playerRepository, times(1)).delete(player);
    }

    @Test
    void readPlayerByIdNotFoundTest() {
        UUID invalidId = UUID.randomUUID();
        when(playerRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> playerService.readById(invalidId))
                .isInstanceOf(NotPlayerExistByIdException.class)
                .hasMessage("No such identifier was found in the player!");

        verify(playerRepository, times(1)).findById(invalidId);
    }
}
