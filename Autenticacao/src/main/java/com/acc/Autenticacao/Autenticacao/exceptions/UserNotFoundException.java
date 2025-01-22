package com.acc.Autenticacao.Autenticacao.exceptions;

import static com.acc.Autenticacao.Autenticacao.docs.ErrorCodes.ResourceNotFoundCodes.USER_NOT_FOUND;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super(USER_NOT_FOUND + username);
    }
}
