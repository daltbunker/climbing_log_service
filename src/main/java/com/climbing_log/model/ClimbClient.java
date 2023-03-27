package com.climbing_log.model;

import com.climbing_log.enums.Grade;

public class ClimbClient {
  private Integer id; 
  private String name;
  private Grade grade;
  private String area;

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
  public Grade getGrade() {
    return grade;
  }
  public void setGrade(Grade grade) {
    this.grade = grade;
  }
  public String getArea() {
    return area;
  }
  public void setArea(String area) {
    this.area = area;
  }
}
