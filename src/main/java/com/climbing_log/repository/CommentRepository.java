package com.climbing_log.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.climbing_log.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
  @Query("SELECT c FROM Comment c WHERE blog.id = ?1")
  public List<Comment> findByBlogId(Integer blogId);
}
