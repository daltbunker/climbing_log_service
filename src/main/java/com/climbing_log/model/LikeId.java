package com.climbing_log.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LikeId implements Serializable {
  @Column(name = "user_id")
  private Integer userId;
  @Column(name = "blog_id")
  private Integer blogId;

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((userId == null) ? 0 : userId.hashCode());
    result = prime * result + ((blogId == null) ? 0 : blogId.hashCode());
    return result;
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    LikeId other = (LikeId) obj;
    if (userId == null) {
      if (other.userId != null)
        return false;
    } else if (!userId.equals(other.userId))
      return false;
    if (blogId == null) {
      if (other.blogId != null)
        return false;
    } else if (!blogId.equals(other.blogId))
      return false;
    return true;
  }
  public Integer getUserId() {
    return userId;
  }
  public void setUserId(Integer userId) {
    this.userId = userId;
  }
  public Integer getBlogId() {
    return blogId;
  }
  public void setBlogId(Integer blogId) {
    this.blogId = blogId;
  }
}
