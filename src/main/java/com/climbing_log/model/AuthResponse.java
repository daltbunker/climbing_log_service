package com.climbing_log.model;

import java.util.Date;

import com.climbing_log.enums.Role;

public class AuthResponse {
  private String token;
  private Date expiration;
  private String username;
  private Role role;

  public String getToken() {
    return this.token;
  }
  public void setToken(String token) {
    this.token = token;
  }
  public Date getExpiration() {
    return this.expiration;
  }
  public void setExpiration(Date expiration) {
    this.expiration = expiration;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public Role getRole() {
    return role;
  }
  public void setRole(Role role) {
    this.role = role;
  }
}
