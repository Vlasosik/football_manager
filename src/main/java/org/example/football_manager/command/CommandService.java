package org.example.football_manager.command;

import lombok.RequiredArgsConstructor;
import org.example.football_manager.command.dto.CommandDTO;
import org.example.football_manager.command.dto.CommandMapper;
import org.example.football_manager.command.exception.NotCommandExistById;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommandService {
    private final CommandRepository commandRepository;

    public void create(CommandDTO commandDTO) {
        commandRepository.save(CommandMapper.toEntity(commandDTO));
    }

    public Command readById(UUID id) {
        return commandRepository.findById(id).orElseThrow(NotCommandExistById::new);
    }

    public void update(CommandDTO commandDTO) {
        commandRepository.findById(commandDTO.getId())
                .orElseThrow(NotCommandExistById::new);
        commandRepository.save(CommandMapper.toEntity(commandDTO));
    }

    public void deleteById(UUID id) {
        Command command = commandRepository.findById(id)
                .orElseThrow(NotCommandExistById::new);
        commandRepository.delete(command);
    }
}
