package com.votos.desafio.exception;

// Exceção disparada quando o usuário tentar votar duas vezes como mesmo ID.
public class VotoRepetidoException extends Exception{
    public VotoRepetidoException(String message) {
        super(message);
    }
}
