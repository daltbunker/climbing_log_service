package com.climbing_log.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.climbing_log.model.Ascent;

@Repository
public interface AscentRepository extends JpaRepository<Ascent, Integer> {
    public List<Ascent> findAll();

    @Query("SELECT a, c.name FROM Ascent as a, Climb as c WHERE a.climb.id = c.id AND a.user.id = ?1")
    public List<Ascent> findAllByUserId(Integer userId);
}
