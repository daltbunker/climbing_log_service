package com.climbing_log.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity()
@Table(name = "comment_blog")
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  @JsonIgnore
  private User user;
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "blog_id")
  @JsonIgnore
  private Blog blog;
  private String text;
  @CreationTimestamp
  private LocalDateTime createdDate;
  
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
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
  public String getText() {
    return text;
  }
  public void setText(String text) {
    this.text = text;
  }
  public LocalDateTime getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }
}
