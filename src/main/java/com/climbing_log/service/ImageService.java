package com.climbing_log.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.climbing_log.exception.ResourceNotFoundException;
import com.climbing_log.model.Image;
import com.climbing_log.repository.ImageRepository;

@Service
public class ImageService {
  @Autowired
  ImageRepository imageRepository;

  public Image addImage(Image newImage) {
    Image image = imageRepository.save(newImage);
    return image;
  }

  public Image getImageById(Integer id) {
    if (id == null) {
      throw new IllegalArgumentException("file id must not be null");
    }
    Optional<Image> image = imageRepository.findById(id);
    if (image.isEmpty()) {
      throw new ResourceNotFoundException("image not found");
    }
    return image.get();
  }
}
