package com.climbing_log.model;

import java.time.LocalDateTime;

import com.climbing_log.enums.ClimbAttempt;

public class AscentClimb {
  private Integer id;
  private String name;
  private String comment;
  private ClimbAttempt attempts;
  private LocalDateTime date;
  private String grade;
  private Boolean over200lbs;

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
  public String getGrade() {
    return grade;
  }
  public void setGrade(String grade) {
    this.grade = grade;
  }
  public Boolean getOver200lbs() {
    return over200lbs;
  }
  public void setOver200lbs(Boolean over200lbs) {
    this.over200lbs = over200lbs;
  }
}
