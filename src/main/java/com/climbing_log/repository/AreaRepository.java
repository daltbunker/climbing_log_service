package com.climbing_log.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.climbing_log.model.Area;
import com.climbing_log.model.AreaPath;

public interface AreaRepository extends JpaRepository<Area, Integer> {

  @Query(
    value = "SELECT id, name, area_id FROM area WHERE name = :name",
    nativeQuery = true
  )
  public Area findByName(String name);

  @Query(
    value = "SELECT id, name, area_id " + 
            "FROM area " +
            "WHERE area_id = :parentId",
    nativeQuery = true
  )
  public List<Area> findChildren(int parentId);

  @Query(
    value = "SELECT id, name, area_id " + 
            "FROM area " +
            "WHERE area_id IS NULL",
    nativeQuery = true
  )
  public List<Area> getCountries();

  @Query(
    value = "SELECT a1.id, a1.name, a1.area_id " + 
            "FROM area as a1, area as a2 " +
            "WHERE a1.area_id = a2.id " +
            "AND a2.name = 'united states'",
    nativeQuery = true
  )
  public List<Area> getStates();

  @Query(nativeQuery = true)
  public List<AreaPath> findAllAreasWithPath(int searchWithCountry, Integer countryId, String query);

  @Query(
    value = "SELECT COUNT(1) FROM area WHERE area_id = :id",
    nativeQuery = true 
  )
  public Integer getChildrenCount(Integer id);

}
