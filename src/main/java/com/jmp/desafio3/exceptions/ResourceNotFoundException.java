package com.jmp.desafio3.exceptions;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
