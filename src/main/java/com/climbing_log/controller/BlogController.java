package com.climbing_log.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
import com.climbing_log.model.Image;
import com.climbing_log.model.Like;
import com.climbing_log.model.LikeId;
import com.climbing_log.model.User;
import com.climbing_log.service.BlogService;
import com.climbing_log.service.CommentService;
import com.climbing_log.service.ImageService;
import com.climbing_log.service.LikeService;
import com.climbing_log.service.UserService;

@RestController
@RequestMapping(path = "/api/blog")
public class BlogController {
  @Autowired
  BlogService blogService;
  @Autowired
  UserService userService;
  @Autowired
  LikeService likeService;
  @Autowired
  CommentService commentService;
  @Autowired
  ImageService imageService;

  @PostMapping(path = "")
  public ResponseEntity<Blog> addBlog(
    @RequestBody Blog blog,
    @RequestParam(required = true, value = "user") String username
  ) {
    blog.setAuthor(username);
    Blog addedBlog = blogService.addBlog(blog);
    return ResponseEntity.ok(addedBlog);
  }

  @GetMapping(path = "")
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

  @PostMapping(path="/{blog_id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<Image> addImage(
    @RequestParam(required = true, value = "image") MultipartFile newImage,
    @PathVariable(required = true, value = "blog_id") Integer blogId
  ) {
    Blog blog = blogService.getBlogById(blogId);
    Image image = new Image();
    if (!newImage.getName().isEmpty()) {
      try {
        byte[] byteImage = newImage.getBytes();
        image.setBytes(byteImage);
      } catch(IOException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
    }
    Image savedImage = imageService.addImage(image);
    blog.setImage(savedImage);
    blogService.updateBlog(blog);
    return ResponseEntity.ok(savedImage);
  }

  @GetMapping(path="/image/{image_id}")
  public ResponseEntity<Image> getImage(
    @PathVariable(required = true, value = "image_id") Integer imageId
  ) {
    Image image = imageService.getImageById(imageId);
    return ResponseEntity.ok(image);
  }

  @PutMapping(path="/{blog_id}")
  public ResponseEntity<Blog> updateBlog(
    @PathVariable(required = true, value = "blog_id") Integer blogId,
    @RequestBody Blog blog
  ) {
    Blog oldBlog = blogService.getBlogById(blogId);
    oldBlog.setTitle(blog.getTitle());
    oldBlog.setBody(blog.getBody());
    Blog savedBlog = blogService.updateBlog(oldBlog);
    return ResponseEntity.ok(savedBlog);
  }

}
