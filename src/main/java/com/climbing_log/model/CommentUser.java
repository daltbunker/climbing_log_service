package com.climbing_log.model;

import java.time.LocalDateTime;

public class CommentUser {
  private Integer id;
  private String username;
  private String text;
  private LocalDateTime createdDate;

  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getText() {
    return text;
  }
  public void setText(String text) {
    this.text = text;
  }
  public LocalDateTime getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }
  
}
