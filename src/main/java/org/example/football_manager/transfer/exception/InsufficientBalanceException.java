package org.example.football_manager.transfer.exception;

public class InsufficientBalanceException extends RuntimeException {
    private static final String MSG = "The team's balance sheet is insufficient for the transfer.";

    public InsufficientBalanceException(String message) {
        super(message);
    }

    public InsufficientBalanceException() {
        super(MSG);
    }
}
