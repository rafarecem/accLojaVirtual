package com.acc.Autenticacao.Autenticacao.exceptions;

import static com.acc.Autenticacao.Autenticacao.docs.ErrorCodes.AuthErrorsCodes.AUTHENTICATION_FAILED;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String username) {
        super(AUTHENTICATION_FAILED + username);
    }
}
