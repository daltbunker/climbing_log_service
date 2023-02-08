package com.climbing_log.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.climbing_log.model.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer>{
  public List<Blog> findAll();
}
