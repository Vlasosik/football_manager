package org.example.football_manager.command.exception;

public class CommandAlreadyExistByNameException extends RuntimeException {
    private static final String MSG = "This name already exist in the command!";

    public CommandAlreadyExistByNameException() {
        super(MSG);
    }

    public CommandAlreadyExistByNameException(String message) {
        super(message);
    }
}
