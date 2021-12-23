package com.kangmin.app.controller.rest;

import com.kangmin.app.config.jwt.JwtProvider;
import com.kangmin.app.config.oauth2.model.AuthProvider;
import com.kangmin.app.model.Account;
import com.kangmin.app.model.payload.ApiResponse;
import com.kangmin.app.model.payload.JwtAuthenticationResponse;
import com.kangmin.app.model.payload.LoginRequest;
import com.kangmin.app.model.payload.RegisterRequest;
import com.kangmin.app.model.security.WebUserRole;
import com.kangmin.app.service.AccountService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AccountService accountService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthController(final AccountService accountService,
                          final AuthenticationManager authenticationManager,
                          final JwtProvider jwtProvider,
                          final PasswordEncoder passwordEncoder) {
        this.accountService = accountService;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(final @Valid @RequestBody LoginRequest loginRequest) {

        final Authentication authentication = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );

        try {
            final Authentication authResult = authenticationManager.authenticate(authentication);

            final String token = jwtProvider.generateToken(authResult);
            final HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set(jwtProvider.getAuthorizationHeader(), jwtProvider.getTokenPrefix() + token);

            return ResponseEntity.ok()
                    .headers(responseHeaders)
                    .body(new JwtAuthenticationResponse(token, true));
            // return new ResponseEntity<>(new JwtAuthenticationResponse(token), HttpStatus.OK);

        } catch (final AuthenticationException e) {
            e.printStackTrace();
            return new ResponseEntity<>(
                    new ApiResponse(false, "Username/Password not Authenticated!"),
                    HttpStatus.OK
            );
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(final @Valid @RequestBody RegisterRequest request) {

        if (accountService.isUsernameExist(request.getUsername())) {
            return new ResponseEntity<>(
                    new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.OK
            );
        }

        if (accountService.isEmailExist(request.getEmail())) {
            return new ResponseEntity<>(
                    new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.OK
            );
        }

        final String uuid = UUID.randomUUID().toString();
        final Account account = Account.builder()
                .id(uuid)
                .email(request.getEmail())
                .username(request.getUsername())
                .displayName(request.getUsername())
                .role(WebUserRole.NORMAL.name())
                .password(passwordEncoder.encode(request.getPassword()))
                .createdTimestamp(System.currentTimeMillis())
                .providerId("")
                .provider(AuthProvider.LOCAL)
                .build();

        final Optional<Account> createdOpt = accountService.createAccount(account);
        if (createdOpt.isPresent()) {
            final URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/api/accounts/u/{username}")
                    .buildAndExpand(account.getUsername()).toUri();

            return ResponseEntity.created(location).body(
                    new ApiResponse(true, "User registered successfully!")
            );
        }

        return new ResponseEntity<>(
                new ApiResponse(false, "Error happened during account creation!"),
                HttpStatus.SERVICE_UNAVAILABLE
        );
    }
}
