package com.climbing_log.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.climbing_log.model.Comment;
import com.climbing_log.repository.CommentRepository;

@Service
public class CommentServiceImpl {
  @Autowired
  private CommentRepository commentRepository;

  public Comment addComment(Comment newComment) {
    Comment comment = commentRepository.save(newComment);
    return comment;
  }

  public List<Comment> getCommentsByBlogId(Integer blogId) {
    List<Comment> commentList = commentRepository.findByBlogId(blogId);
    if (commentList == null) {
      return new ArrayList<>();
    }
    return commentList;
  }
}
