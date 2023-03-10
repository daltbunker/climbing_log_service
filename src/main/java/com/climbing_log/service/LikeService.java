package com.climbing_log.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.climbing_log.model.Like;
import com.climbing_log.model.LikeId;
import com.climbing_log.repository.LikeRepository;

@Service
public class LikeService {
  @Autowired
  private LikeRepository likeRepository;

  public Like addLike(Like newLike) {
    Like like = likeRepository.save(newLike);
    return like;
  }

  public List<Like> getLikesByBlogId(Integer blogId) {
    List<Like> likeList = likeRepository.findByBlogId(blogId);
    if (likeList == null) {
      return new ArrayList<>();
    }
    return likeList;
  }

  @Transactional
  public void deleteLike(LikeId likeId) {
    likeRepository.deleteByIds(likeId.getBlogId(), likeId.getUserId());
  }
}
