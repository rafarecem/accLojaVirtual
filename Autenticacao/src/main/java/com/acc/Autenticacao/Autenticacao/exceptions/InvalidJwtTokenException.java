package com.acc.Autenticacao.Autenticacao.exceptions;

import static com.acc.Autenticacao.Autenticacao.docs.ErrorCodes.TokenErrorsCodes.INVALID_TOKEN;

public class InvalidJwtTokenException extends RuntimeException {
    public InvalidJwtTokenException() {
        super(INVALID_TOKEN);
    }
}
