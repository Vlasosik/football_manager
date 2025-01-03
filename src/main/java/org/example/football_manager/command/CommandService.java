package org.example.football_manager.command;

import lombok.RequiredArgsConstructor;
import org.example.football_manager.command.dto.CommandDTO;
import org.example.football_manager.command.dto.CommandMapper;
import org.example.football_manager.command.exception.NotCommandExistByIdException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommandService {
    private final CommandRepository commandRepository;

    public void create(CommandDTO commandDTO) {
        commandRepository.save(CommandMapper.toEntity(commandDTO));
    }

    public CommandDTO readById(UUID id) {
        return CommandMapper.toDto(commandRepository.findById(id).orElseThrow(NotCommandExistByIdException::new));
    }

    public boolean existsByName(String name) {
        return commandRepository.existsByName(name);
    }

    public void update(CommandDTO commandDTO) {
        commandRepository.findById(commandDTO.getId())
                .orElseThrow(NotCommandExistByIdException::new);
        commandRepository.save(CommandMapper.toEntity(commandDTO));
    }

    public void deleteById(UUID id) {
        Command command = commandRepository.findById(id)
                .orElseThrow(NotCommandExistByIdException::new);
        commandRepository.delete(command);
    }
}
