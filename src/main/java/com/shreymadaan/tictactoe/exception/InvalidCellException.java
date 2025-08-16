package com.shreymadaan.tictactoe.exception;

public class InvalidCellException extends RuntimeException {
    public InvalidCellException(String message) {
        super(message);
    }
}
