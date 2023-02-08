package com.climbing_log.model;

public class ClimbLocation {
  private Integer id;
  private String name;
  private String type;
  private String grade;
  private String sector;
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
  public String getSector() {
    return sector;
  }
  public void setSector(String sector) {
    this.sector = sector;
  }
  public String getArea() {
    return area;
  }
  public void setArea(String area) {
    this.area = area;
  }
}
