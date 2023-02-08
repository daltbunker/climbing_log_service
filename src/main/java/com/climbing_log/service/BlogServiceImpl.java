package com.climbing_log.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.climbing_log.exception.ResourceNotFoundException;
import com.climbing_log.model.Blog;
import com.climbing_log.model.BlogPage;
import com.climbing_log.model.BlogThumbnail;
import com.climbing_log.model.Comment;
import com.climbing_log.model.CommentUser;
import com.climbing_log.model.Like;
import com.climbing_log.model.User;
import com.climbing_log.repository.BlogRepository;
import com.climbing_log.service.ifc.BlogService;

@Service
public class BlogServiceImpl implements BlogService {
  @Autowired
  BlogRepository blogRepository;
  @Autowired
  CommentServiceImpl commentService;
  @Autowired
  LikeServiceImpl likeService;
  @Autowired
  UserServiceImpl userService;

  @Override
  public Blog addBlog(Blog newBlog) {
    Blog blog = blogRepository.save(newBlog);
    return blog;
  }

  @Override
  public Blog updateBlog(Blog newBlog) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Blog getBlogById(Integer id) {
    if (id == null) {
      throw new IllegalArgumentException("blog id must not be null");
    }
    Optional<Blog> blogOpt = blogRepository.findById(id);
    if(blogOpt.isEmpty()) {
      throw new ResourceNotFoundException("blog not found");
    }
    Blog blog = blogOpt.get();
    return blog;
  }
  
  public BlogPage getBlogPageById(Integer id) {
    Blog blog = this.getBlogById(id);
    BlogPage blogPage = new BlogPage();
    blogPage.setComments(this.getCommentsWithUsername(id));
    blogPage.setLikes(this.getLikesWithUsername(id));
    blogPage.setId(id);
    blogPage.setAuthor(blog.getAuthor());
    blogPage.setCreatedDate(blog.getCreatedDate());
    blogPage.setImage(blog.getImage());
    blogPage.setBody(blog.getBody());
    blogPage.setTitle(blog.getTitle());
    return blogPage;
  }

  private List<String> getLikesWithUsername(Integer id) {
    List<Like> likes = likeService.getLikesByBlogId(id);
    List<String> likesWithUsername = likes.stream()
      .map(Like::getUser)
      .map(User::getUsername)
      .collect(Collectors.toList());
    return likesWithUsername;
  }

  private List<CommentUser> getCommentsWithUsername(Integer id) {
    List<Comment> comments = commentService.getCommentsByBlogId(id);
    List<CommentUser> commentsWithUsername = new ArrayList<>();
    for(Comment comment: comments) {
      CommentUser commentWithUsername = new CommentUser();
      commentWithUsername.setId(id);
      commentWithUsername.setText(comment.getText());
      commentWithUsername.setUsername(comment.getUser().getUsername());
      commentWithUsername.setCreatedDate(comment.getCreatedDate());
      commentsWithUsername.add(commentWithUsername);
    }
    return commentsWithUsername;
  }

  @Override
  public List<Blog> getAllBlogs() {
    List<Blog> blogs = blogRepository.findAll();
    if (blogs == null || blogs.isEmpty()) {
      throw new ResourceNotFoundException("blogs not found");
    }
    return blogs;
  }

  public List<BlogThumbnail> getAllBlogPages() {
    List<Blog> blogs = this.getAllBlogs();
    List<BlogThumbnail> blogThumbnails = new ArrayList<>();
    for (Blog blog: blogs) {
      BlogThumbnail blogThumbnail = new BlogThumbnail();
      List<Comment> comments = commentService.getCommentsByBlogId(blog.getId());
      List<Like> likes = likeService.getLikesByBlogId(blog.getId());
      blogThumbnail.setCommentCount(comments.size());
      blogThumbnail.setLikeCount(likes.size());
      blogThumbnail.setId(blog.getId());
      blogThumbnail.setCreatedDate(blog.getCreatedDate());
      blogThumbnail.setImage(blog.getImage());
      blogThumbnail.setTitle(blog.getTitle());
      if (blog.getBody().length() > 350) {
        String trimmedBody = blog.getBody().substring(0, 350);
        blogThumbnail.setBody(trimmedBody);
      } else {
        blogThumbnail.setBody(blog.getBody());
      }
      blogThumbnails.add(blogThumbnail);
    }
    return blogThumbnails;
  }
}
