package com.climbing_log.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.climbing_log.model.Like;

@Repository
public interface LikeRepository extends JpaRepository<Like, Integer> {
  @Query("SELECT l FROM Like l WHERE blog.id = ?1")
  public List<Like> findByBlogId(Integer blogId);
  
  @Modifying
  @Query("DELETE FROM Like l WHERE blog.id = ?1 AND user.id = ?2")
  public void deleteByIds(Integer blogId, Integer userId);
}
