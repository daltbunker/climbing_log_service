package com.climbing_log.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.climbing_log.enums.Role;
import com.climbing_log.model.AuthResponse;
import com.climbing_log.model.AuthRequest;
import com.climbing_log.model.User;
import com.climbing_log.repository.UserRepository;
import com.climbing_log.security.JwtService;
import com.climbing_log.service.ifc.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
  @Autowired
  UserRepository userRepository;
  @Autowired
  PasswordEncoder passwordEncoder;
  @Autowired
  JwtService jwtService;
  @Autowired
  AuthenticationManager authenticationManager;

  public AuthResponse register(AuthRequest request) {
    User user = new User();
    user.setUsername(request.getUsername());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setRole(Role.USER);
    userRepository.save(user);
    String jwtToken = jwtService.generateToken(user);
    Date expiration = jwtService.extractExpiration(jwtToken);
    AuthResponse authenticationResponse = new AuthResponse();
    authenticationResponse.setToken(jwtToken);
    authenticationResponse.setExpiration(expiration);
    authenticationResponse.setUsername(user.getUsername());
    authenticationResponse.setRole(user.getRole());
    return authenticationResponse;
  }

  public AuthResponse authenticate(AuthRequest request) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
          request.getUsername(),
          request.getPassword()
      )
    );
    var user = userRepository.findByUsername(request.getUsername())
        .orElseThrow();
    String jwtToken = jwtService.generateToken(user);
    Date expiration = jwtService.extractExpiration(jwtToken);
    AuthResponse authenticationResponse = new AuthResponse();
    authenticationResponse.setToken(jwtToken);
    authenticationResponse.setExpiration(expiration);
    authenticationResponse.setUsername(user.getUsername());
    authenticationResponse.setRole(user.getRole());
    return authenticationResponse;
  }
}
