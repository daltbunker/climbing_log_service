package com.climbing_log.service.ifc;

import com.climbing_log.model.User;

public interface UserService {
  User getUserByUsername(String username);
}
