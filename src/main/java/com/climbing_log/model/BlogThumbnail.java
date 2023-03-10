package com.climbing_log.model;

import java.time.LocalDateTime;

public class BlogThumbnail {
  private Integer id;
  private String body;
  private String title;
  private LocalDateTime createdDate;
  private Integer commentCount;
  private Integer likeCount;
  private Integer imageId;

  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
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

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }
  public Integer getCommentCount() {
    return commentCount;
  }
  public void setCommentCount(Integer commentCount) {
    this.commentCount = commentCount;
  }
  public Integer getLikeCount() {
    return likeCount;
  }
  public void setLikeCount(Integer likeCount) {
    this.likeCount = likeCount;
  }
  public Integer getImageId() {
    return imageId;
  }
  public void setImageId(Integer imageId) {
    this.imageId = imageId;
  }
}
