package com.climbing_log.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.climbing_log.model.Climb;

@Repository
public interface ClimbRepository extends JpaRepository<Climb, Integer> {
    public List<Climb> findAll();

    @Query("SELECT c FROM Climb c WHERE location.id = ?1")
    public List<Climb> findClimbsByLocation(Integer id);

    @Query("SELECT c FROM Climb c WHERE c.name = ?1")
    public List<Climb> findClimbsByName(String name);
}