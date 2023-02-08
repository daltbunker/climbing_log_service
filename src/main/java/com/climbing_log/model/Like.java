package com.climbing_log.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity()
@Table(name = "like_blog")
public class Like {
  @EmbeddedId
  private LikeId id;
  @ManyToOne(cascade = CascadeType.ALL)
  @MapsId("userId")
  @JoinColumn(name = "user_id")
  @JsonIgnore
  private User user;
  @ManyToOne(cascade = CascadeType.ALL)
  @MapsId("blogId")
  @JsonIgnore
  private Blog blog;
  @CreationTimestamp
  private LocalDateTime createdDate;

  public LikeId getId() {
    return id;
  }
  public void setId() {
    LikeId likeId = new LikeId();
    likeId.setBlogId(blog.getId());
    likeId.setUserId(user.getId());
    this.id = likeId;
  }
  public User getUser() {
    return user;
  }
  public void setUser(User user) {
    this.user = user;
  }
  public Blog getBlog() {
    return blog;
  }
  public void setBlog(Blog blog) {
    this.blog = blog;
  }
  public LocalDateTime getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }
}
