package com.climbing_log.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "blog")
@EntityListeners(AuditingEntityListener.class)
public class Blog {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  @JsonIgnore
  private User user;

  private String author;

  @CreationTimestamp
  private LocalDateTime createdDate;

  private String body;
  private String title;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "image_id")
  @JsonIgnore
  private Image image;

  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public User getUser() {
    return this.user;
  }
  public void setUser (User user) {
    this.user = (user);
  }
  public String getAuthor() {
    if (this.author == null) {
      return this.user.getUsername();
    }
    return this.author;
  }
  public void setAuthor(String name) {
    this.author = name;
  }
  public LocalDateTime getCreatedDate() {
    return this.createdDate;
  }
  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }
  public String getBody() {
    return this.body;
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
  public Image getImage() {
    return image;
  }
  public void setImage(Image image) {
    this.image = image;
  }
}
