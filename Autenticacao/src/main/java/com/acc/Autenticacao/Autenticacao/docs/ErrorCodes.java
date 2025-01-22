package com.acc.Autenticacao.Autenticacao.docs;

public class ErrorCodes {
    public static class ResourceNotFoundCodes {
        public static final String USER_NOT_FOUND = "4040 - User Not Found: ";
    }

    public static class UserErrorsCodes {
        public static final String USER_ALREADY_EXISTS = "40011 - User Already Exists";
    }

    public static class TokenErrorsCodes {
        public static final String INVALID_TOKEN = "40021 - Invalid Token";
    }

    public static class AuthErrorsCodes {
        public static final String AUTHENTICATION_FAILED = "4011 - Authentication Failed: ";
    }

    public static class InternalError {
        public static final String INTERNAL_SERVER_ERROR = "5001 - Internal Server Error: ";
        public static final String FAILED_READING_PRIVATE_KEY = "5002 - Failed Reading Private Key";
        public static final String FAILED_READING_PUBLIC_KEY = "5003 - Failed Reading Public Key";
        public static final String PRIVATE_KEY_ENCRYPT_ERROR = "5004 -Private key is not correctly encoded in Base64";
    }
}
