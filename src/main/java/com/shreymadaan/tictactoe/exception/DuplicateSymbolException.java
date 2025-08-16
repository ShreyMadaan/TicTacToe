package com.shreymadaan.tictactoe.exception;

public class DuplicateSymbolException extends RuntimeException{
    public DuplicateSymbolException(String message){
        super(message);
    }
    public DuplicateSymbolException(){

    }
    public DuplicateSymbolException(String message, Throwable cause){
        super(message, cause);
    }
}
