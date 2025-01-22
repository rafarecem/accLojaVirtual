package com.acc.Autenticacao.Autenticacao.controller;

import com.acc.Autenticacao.Autenticacao.model.AuthenticationRequest;
import com.acc.Autenticacao.Autenticacao.model.AuthenticationResponse;
import com.acc.Autenticacao.Autenticacao.model.Token;
import com.acc.Autenticacao.Autenticacao.model.User;
import com.acc.Autenticacao.Autenticacao.repository.UserRepository;
import com.acc.Autenticacao.Autenticacao.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.acc.Autenticacao.Autenticacao.docs.ControllerDocs.AuthControllerDocs.LoginEndpointDocs.*;
import static com.acc.Autenticacao.Autenticacao.docs.ControllerDocs.AuthControllerDocs.RegisterEndpointDocs.*;
import static com.acc.Autenticacao.Autenticacao.docs.ControllerDocs.AuthControllerDocs.TAG_DESCRIPTION;
import static com.acc.Autenticacao.Autenticacao.docs.ControllerDocs.AuthControllerDocs.TAG_NAME;
import static com.acc.Autenticacao.Autenticacao.docs.ControllerDocs.CONTENT;
import static com.acc.Autenticacao.Autenticacao.docs.ControllerDocs.HeaderName.AUTHORIZATION;
import static com.acc.Autenticacao.Autenticacao.docs.ControllerDocs.StatusCode.*;
import static com.acc.Autenticacao.Autenticacao.docs.ErrorCodes.AuthErrorsCodes.AUTHENTICATION_FAILED;
import static com.acc.Autenticacao.Autenticacao.docs.ErrorCodes.UserErrorsCodes.USER_ALREADY_EXISTS;

@RestController
@RequestMapping("/v1/auth")
@Tag(
        name = TAG_NAME,
        description = TAG_DESCRIPTION
)
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(
            summary = LOGIN_SUMMARY,
            description = LOGIN_DESCRIPTION
    ) @ApiResponses(value = {
            @ApiResponse(
                    responseCode = OK,
                    description = LOGIN_SUCCESS,
                    content = @Content(mediaType = CONTENT)),
            @ApiResponse(
                    responseCode = UNAUTHORIZED,
                    description = AUTHENTICATION_FAILED + "USERNAME",
                    content = @Content(mediaType = CONTENT))
    })
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        AuthenticationResponse response = authService.authenticate(authenticationRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.set(AUTHORIZATION, "Bearer " + response.getToken());
        return ResponseEntity.ok()
                .headers(headers)
                .body(response);
    }

    @Operation(summary = REGISTER_SUMMARY, description = REGISTER_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = OK,
                    description = REGISTER_SUCCESS,
                    content = @Content(mediaType = CONTENT)),
            @ApiResponse(
                    responseCode = BAD_REQUEST,
                    description = USER_ALREADY_EXISTS,
                    content = @Content(mediaType = CONTENT))
    })
    @PostMapping("/cadastro")
    public ResponseEntity<Void> cadastro(@RequestBody AuthenticationRequest authenticationRequest) {
        authService.register(authenticationRequest);
        return ResponseEntity.noContent().build();
    }

}
