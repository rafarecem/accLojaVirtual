package com.acc.Autenticacao.Autenticacao.exceptions;

import static com.acc.Autenticacao.Autenticacao.docs.ErrorCodes.UserErrorsCodes.USER_ALREADY_EXISTS;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super(USER_ALREADY_EXISTS);
    }
}
