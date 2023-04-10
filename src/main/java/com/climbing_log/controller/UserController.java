package com.climbing_log.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.climbing_log.model.AuthResponse;
import com.climbing_log.model.AuthRequest;
import com.climbing_log.service.AuthenticationService;

@RestController
@RequestMapping(path = "/api")
public class UserController {
    @Autowired
    AuthenticationService authenticationService;
    
    @PostMapping(path = "/auth/signup")
    public ResponseEntity<AuthResponse> registerUser(
            @RequestBody AuthRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping(path = "/auth/login")
    public ResponseEntity<AuthResponse> authenticateUser(
            @RequestBody AuthRequest request) throws AuthenticationException {
        try {
            AuthResponse authResponse = authenticationService.authenticate(request);
            return ResponseEntity.ok(authResponse);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
