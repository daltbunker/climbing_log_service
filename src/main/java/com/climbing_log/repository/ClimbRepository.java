package com.climbing_log.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.climbing_log.model.Climb;

@Repository
public interface ClimbRepository extends JpaRepository<Climb, Integer> {
    public List<Climb> findAll();

    @Query("SELECT c FROM Climb c WHERE area.id = ?1")
    public List<Climb> findClimbsByAreaId(Integer id);

    @Query("SELECT c FROM Climb c WHERE c.name = ?1")
    public List<Climb> findClimbsByName(String name);

    @Query("SELECT COUNT(1) FROM Climb c WHERE area.id = ?1")
    public Integer getCountByArea(Integer id);

    @Query("SELECT DISTINCT c.name FROM Climb c WHERE c.name LIKE :query%")
    public List<String> findClimbsByQuery(String query);
}