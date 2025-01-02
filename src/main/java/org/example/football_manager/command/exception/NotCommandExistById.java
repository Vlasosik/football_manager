package org.example.football_manager.command.exception;

public class NotCommandExistById extends RuntimeException {
    private static final String MSG = "No such identifier was found in the command!";
    public NotCommandExistById(String message) {
        super(message);
    }

    public NotCommandExistById() {
        super(MSG);
    }
}
