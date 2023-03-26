package com.climbing_log.model;

public class AreaPath {

  public AreaPath(int id, String name, String path, String pathIds) {
    this.id = id;
    this.name = name;
    this.path = path;
    this.pathIds = pathIds;
  }

  private int id;
  private String name;
  private String path;
  private String pathIds;
  
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getPath() {
    return path;
  }
  public void setPath(String path) {
    this.path = path;
  }
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getPathIds() {
    return pathIds;
  }
  public void setPathIds(String pathIds) {
    this.pathIds = pathIds;
  }
}
