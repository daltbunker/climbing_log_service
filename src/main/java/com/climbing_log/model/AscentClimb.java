package com.climbing_log.model;

import java.time.LocalDateTime;

import com.climbing_log.enums.ClimbAttempt;

public class AscentClimb {
  private Integer id;
  private String name;
  private String comment;
  private ClimbAttempt attempts;
  private LocalDateTime date;

  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getComment() {
    return comment;
  }
  public void setComment(String comment) {
    this.comment = comment;
  }
  public ClimbAttempt getAttempts() {
    return attempts;
  }
  public void setAttempts(ClimbAttempt attempts) {
    this.attempts = attempts;
  }
  public LocalDateTime getDate() {
    return date;
  }
  public void setDate(LocalDateTime date) {
    this.date = date;
  }
}
