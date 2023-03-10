package com.climbing_log.repository;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.climbing_log.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
}
