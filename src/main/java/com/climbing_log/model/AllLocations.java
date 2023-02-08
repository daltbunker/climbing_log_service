package com.climbing_log.model;

import java.util.List;

public class AllLocations {
 private List<String> sectors; 
 private List<String> areas; 
 
 public void setAreas(List<String> areas) {
     this.areas = areas;
 }
 public List<String> getAreas() {
     return areas;
 }
 public void setSectors(List<String> sectors) {
     this.sectors = sectors;
 }
 public List<String> getSectors() {
     return sectors;
 }
}
