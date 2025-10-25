package com.jmp.desafio3.exceptions;

@SuppressWarnings("serial")
public class MethodArgumentNotValidException extends RuntimeException{
    public MethodArgumentNotValidException(String message) {
        super(message);
    }
}
