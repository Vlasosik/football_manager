package org.example.football_manager.transfer.exception;

public class NotTransferExistByIdException extends RuntimeException {
    private static final String MSG = "No such identifier was found in the transfer!";

    public NotTransferExistByIdException() {
        super(MSG);
    }

    public NotTransferExistByIdException(String message) {
        super(message);
    }
}
