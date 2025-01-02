package org.example.football_manager.player.exception;

public class NotPlayerExistById extends RuntimeException {
    private static final String MSG = "No such identifier was found in the command!";

    public NotPlayerExistById(String message) {
        super(message);
    }

    public NotPlayerExistById() {
        super(MSG);
    }
}
