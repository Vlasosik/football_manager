package org.example.football_manager.command;

import org.example.football_manager.command.dto.CommandDTO;
import org.example.football_manager.command.dto.CommandMapper;
import org.example.football_manager.command.exception.NotCommandExistByIdException;
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
public class CommandServiceTest {
    @Mock
    private CommandRepository commandRepository;
    @InjectMocks
    private CommandService commandService;

    private Command command;

    @BeforeEach
    void setUp() {
        command = new Command();
        command.setId(UUID.fromString("9de5d04c-7714-494c-8518-90fc7bec1e3d"));
        command.setName("Malorka");
        command.setBalance(BigDecimal.valueOf(1000000.00));
        command.setCommissionRate(BigDecimal.valueOf(1.50));
    }

    @Test
    void saveCommandTest() {
        when(commandRepository.save(any(Command.class))).thenReturn(command);
        CommandDTO commandDTO = CommandMapper.toDto(command);
        commandService.create(commandDTO);
        verify(commandRepository, times(1)).save(any(Command.class));
        assertNotNull(commandDTO);
        assertEquals(command.getId(), commandDTO.getId());
    }

    @Test
    void readCommandByIdTest() {
        when(commandRepository.findById(command.getId())).thenReturn(Optional.of(command));
        CommandDTO commandDTO = commandService.readById(command.getId());
        verify(commandRepository, times(1)).findById(command.getId());
        assertNotNull(commandDTO);
        assertEquals(command.getId(), commandDTO.getId());
    }

    @Test
    void updateCommandByIdTest() {
        when(commandRepository.findById(command.getId())).thenReturn(Optional.of(command));
        when(commandRepository.save(any(Command.class))).thenReturn(command);

        CommandDTO commandDTO = CommandMapper.toDto(command);
        commandService.update(commandDTO);

        verify(commandRepository, times(1)).findById(command.getId());
        verify(commandRepository, times(1)).save(any(Command.class));
    }

    @Test
    void deleteCommandByIdTest() {
        when(commandRepository.findById(command.getId())).thenReturn(Optional.of(command));
        commandService.deleteById(command.getId());
        verify(commandRepository, times(1)).delete(command);
    }

    @Test
    void readCommandByIdNotFoundTest() {
        UUID invalidId = UUID.randomUUID();
        when(commandRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> commandService.readById(invalidId))
                .isInstanceOf(NotCommandExistByIdException.class)
                .hasMessage("No such identifier was found in the command!");

        verify(commandRepository, times(1)).findById(invalidId);
    }
}
