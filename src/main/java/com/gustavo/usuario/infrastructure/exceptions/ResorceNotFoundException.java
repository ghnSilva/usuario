package com.gustavo.usuario.infrastructure.exceptions;

public class ResorceNotFoundException extends RuntimeException {
    public ResorceNotFoundException(String message) {

        super(message);
    }
    public ResorceNotFoundException(String mensagem, Throwable throwable){
        super(mensagem, throwable);
    }
}
