package com.climbing_log.service.ifc;

import java.util.List;

import com.climbing_log.model.Blog;

public interface BlogService {
  Blog addBlog(Blog newBlog);

  Blog updateBlog(Blog newBlog);

  Blog getBlogById(Integer id);

  List<Blog> getAllBlogs();
}
