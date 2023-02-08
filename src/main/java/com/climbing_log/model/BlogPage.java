package com.climbing_log.model;

import java.time.LocalDateTime;
import java.util.List;

public class BlogPage {
  private Integer id;
  private String author;
  private String body;
  private String title;
  private byte[] image;
  private LocalDateTime createdDate;
  private List<CommentUser> comments;
  private List<String> likes;

  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public String getAuthor() {
    return author;
  }
  public void setAuthor(String author) {
    this.author = author;
  }
  public String getBody() {
    return body;
  }
  public void setBody(String body) {
    this.body = body;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public byte[] getImage() {
    return image;
  }
  public void setImage(byte[] image) {
    this.image = image;
  }
  public LocalDateTime getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }
  public List<CommentUser> getComments() {
    return comments;
  }
  public void setComments(List<CommentUser> comments) {
    this.comments = comments;
  }
  public List<String> getLikes() {
    return likes;
  }
  public void setLikes(List<String> likes) {
    this.likes = likes;
  }
}