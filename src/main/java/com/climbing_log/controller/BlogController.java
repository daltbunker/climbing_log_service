package com.climbing_log.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.climbing_log.model.Blog;
import com.climbing_log.model.BlogPage;
import com.climbing_log.model.BlogThumbnail;
import com.climbing_log.model.Comment;
import com.climbing_log.model.CommentUser;
import com.climbing_log.model.Like;
import com.climbing_log.model.LikeId;
import com.climbing_log.model.User;
import com.climbing_log.service.BlogServiceImpl;
import com.climbing_log.service.CommentServiceImpl;
import com.climbing_log.service.LikeServiceImpl;
import com.climbing_log.service.UserServiceImpl;

@RestController
@RequestMapping(path = "/api/blog")
public class BlogController {
  @Autowired
  BlogServiceImpl blogService;
  @Autowired
  UserServiceImpl userService;
  @Autowired
  LikeServiceImpl likeService;
  @Autowired
  CommentServiceImpl commentService;

  @PostMapping(path = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<Blog> addBlog(
    @RequestParam(required = true, value = "image") MultipartFile image,
    @RequestParam(required = true, value = "title") String title,
    @RequestParam(required = true, value = "body") String body,
    @RequestParam(required = true, value = "user") String username
  ) {
    Blog blog = new Blog();
    if (!image.getName().isEmpty()) {
      try {
        byte[] byteImage = image.getBytes();
        blog.setImage(byteImage);
      } catch(IOException e) {

      }
    }
    blog.setTitle(title);
    blog.setBody(body);
    User user = userService.getUserByUsername(username);
    blog.setAuthor(user.getUsername());
    Blog addedBlog = blogService.addBlog(blog);
    return ResponseEntity.ok(addedBlog);
  }

  @GetMapping(path = "/all")
  public ResponseEntity<List<BlogThumbnail>> getAllBlogs() {
    List<BlogThumbnail> blogThumbnails = blogService.getAllBlogPages();
    return ResponseEntity.ok(blogThumbnails);
  }

  @GetMapping(path = "/{blog_id}")
  public ResponseEntity<BlogPage> getBlogById(
    @PathVariable(name = "blog_id") Integer blogId
  ) {
    BlogPage blogPage = blogService.getBlogPageById(blogId);
    return ResponseEntity.ok(blogPage);
  }

  @PostMapping(path = "/{blog_id}/like")
  public ResponseEntity<Like> addLike(
    @RequestParam(required = true, value = "user") String username,
    @PathVariable(required = true, value = "blog_id") Integer blogId
  ) {
    User user = userService.getUserByUsername(username);
    Blog blog = blogService.getBlogById(blogId);
    Like like = new Like();
    like.setUser(user);
    like.setBlog(blog);
    like.setId();
    likeService.addLike(like);
    return ResponseEntity.ok(like);
  }

  @DeleteMapping(path = "/{blog_id}/like")
  public ResponseEntity<String> deleteLike(
    @RequestParam(required = true, value = "user") String username,
    @PathVariable(required = true, value = "blog_id") Integer blogId
  ) {
    User user = userService.getUserByUsername(username);
    LikeId likeId = new LikeId();
    likeId.setBlogId(blogId);
    likeId.setUserId(user.getId());
    likeService.deleteLike(likeId);
    return ResponseEntity.status(204).build();   
  }

  @PostMapping(path = "/{blog_id}/comment")
  public ResponseEntity<CommentUser> addComment(
    @RequestParam(required = true, value = "user") String username,
    @PathVariable(required = true, value = "blog_id") Integer blogId,
    @RequestBody() @Valid Comment newComment
  ) {
    User user = userService.getUserByUsername(username);
    Blog blog = blogService.getBlogById(blogId);
    newComment.setUser(user);
    newComment.setBlog(blog);
    Comment comment = commentService.addComment(newComment);

    CommentUser commentUser = new CommentUser();
    commentUser.setCreatedDate(comment.getCreatedDate());
    commentUser.setId(comment.getId());
    commentUser.setText(comment.getText());
    commentUser.setUsername(user.getUsername());

    return ResponseEntity.ok(commentUser);
  } 
}
