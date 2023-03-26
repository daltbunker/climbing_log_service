package com.climbing_log.model;

import java.util.List;

public class ClimbRequest {
  private String name;
  private String type;
  private Integer countryId;
  private List<Path> path;
  private String grade;

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getGrade() {
    return grade;
  }
  public void setGrade(String grade) {
    this.grade = grade;
  }
  public Integer  getCountryId() {
    return countryId;
  }
  public void setCountryId(Integer countryId) {
    this.countryId = countryId;
  }
  public List<Path> getPath() {
    return path;
  }
  public void setPath(List<Path> path) {
    this.path = path;
  }
}
