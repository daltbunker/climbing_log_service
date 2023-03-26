package com.climbing_log.model;

import java.util.List;

public class AreaClient {
  private int id;
  private String name;
  private int climbCount;
  private List<Path> path;

  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public int getClimbCount() {
    return climbCount;
  }
  public void setClimbCount(int climbCount) {
    this.climbCount = climbCount;
  }
  public List<Path> getPath() {
    return path;
  }
  public void setPath(List<Path> path) {
    this.path = path;
  }
}
