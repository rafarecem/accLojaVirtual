package com.acc.Autenticacao.Autenticacao.exceptions;

public class KeyLoadingException extends RuntimeException {
    public KeyLoadingException(String message) {
        super(message);
    }

    public KeyLoadingException(String message, Throwable cause) {
        super(message, cause);
    }
}

