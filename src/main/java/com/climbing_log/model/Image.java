package com.climbing_log.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "image")
public class Image {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @Lob
  private byte[] bytes;
  
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public byte[] getBytes() {
    return bytes;
  }
  public void setBytes(byte[] bytes) {
    this.bytes = bytes;
  }
}
