package com.climbing_log.service.ifc;

import com.climbing_log.model.AuthResponse;
import com.climbing_log.model.AuthRequest;

public interface AuthenticationService {
  AuthResponse register(AuthRequest request);

  AuthResponse authenticate(AuthRequest request);
}
