package org.example.football_manager.player.exception;

public class NotPlayerExistByIdException extends RuntimeException {
    private static final String MSG = "No such identifier was found in the player!";

    public NotPlayerExistByIdException(String message) {
        super(message);
    }

    public NotPlayerExistByIdException() {
        super(MSG);
    }
}
