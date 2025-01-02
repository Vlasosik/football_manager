package org.example.football_manager.command.dto;

import org.example.football_manager.command.Command;

public class CommandMapper {

    public static CommandDTO toDto(Command command) {
        return new CommandDTO(
                command.getId(),
                command.getName(),
                command.getBalance(),
                command.getCommissionRate()
        );
    }

    public static Command toEntity(CommandDTO commandDTO) {
        return new Command(
                commandDTO.getId(),
                commandDTO.getName(),
                commandDTO.getBalance(),
                commandDTO.getCommissionRate()
        );
    }
}
