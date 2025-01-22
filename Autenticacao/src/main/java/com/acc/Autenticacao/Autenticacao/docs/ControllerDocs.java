package com.acc.Autenticacao.Autenticacao.docs;

public class ControllerDocs {

    public static final String SECURITY_REQUERIMENT = "Bearer Authentication";
    public static final String CONTENT = "application/json";

    public class StatusCode {
        public static final String OK = "200";
        public static final String CREATE = "201";
        public static final String NON_AUTHORITATIVE_INFORMATION = "203";
        public static final String NO_CONTENT = "204";
        public static final String ACCEPTED = "208";
        public static final String FOUND = "302";
        public static final String BAD_REQUEST = "400";
        public static final String UNAUTHORIZED = "401";
        public static final String PAYMENT_REQUIRED = "402";
        public static final String FORBIDDEN = "403";
        public static final String NOT_FOUND = "404";
    }

    public class HeaderName {
        public static final String ACCEPT = "Accept";
        public static final String ACCEPT_CHARSET = "Accept-Charset";
        public static final String ACCEPT_ENCODING = "Accept-Encoding";
        public static final String ACCEPT_LANGUAGE = "Accept-Language";
        public static final String AUTHORIZATION = "Authorization";
        public static final String CACHE_CONTROL = "Cache-Control";
        public static final String CONTENT_LENGTH = "Content-Length";
        public static final String CONTENT_TYPE = "Content-Type";
        public static final String EXPIRES = "Expires";
        public static final String HOST = "Host";
        public static final String LOCATION = "Location";
        public static final String PRAGMA = "Pragma";
        public static final String PROXY_AUTHORIZATION = "Proxy-Authorization";
        public static final String RANGE = "Range";
        public static final String REFERER = "Referer";
        public static final String SERVER = "Server";
        public static final String TRANSFER_ENCODING = "Transfer-Encoding";
    }

    public class SchemaType {
        public static final String LINE = " \n \n ";
        public static final String TOPIC = " \n - ";
        public static final String STRING = "string";
        public static final String URL = "url";
        public static final String POSSIBLE_ERRO = "Possible errors: " + TOPIC;

    }

    public class Content {
        public static final String JSON = "application/json";
        public static final String TEXT_PLAIN = "text/plain";
    }

    public class AuthControllerDocs {

        public static final String TAG_NAME = "Authentication Management";
        public static final String TAG_DESCRIPTION = "Controller for managing authentication and user registration";

        public class LoginEndpointDocs {
            public static final String LOGIN_SUMMARY = "Authenticate User";
            public static final String LOGIN_DESCRIPTION = "Authenticate user and return a JWT token";
            public static final String LOGIN_SUCCESS = "Successfully authenticated";
        }

        public class RegisterEndpointDocs {
            public static final String REGISTER_SUMMARY = "Register User";
            public static final String REGISTER_DESCRIPTION = "Register a new user in the system";
            public static final String REGISTER_SUCCESS = "User registered successfully";
        }

    }
}
