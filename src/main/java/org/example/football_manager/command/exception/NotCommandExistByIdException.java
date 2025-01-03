package org.example.football_manager.command.exception;

public class NotCommandExistByIdException extends RuntimeException {
    private static final String MSG = "No such identifier was found in the command!";

    public NotCommandExistByIdException(String message) {
        super(message);
    }

    public NotCommandExistByIdException() {
        super(MSG);
    }
}
