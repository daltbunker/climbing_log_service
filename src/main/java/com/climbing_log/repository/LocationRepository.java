package com.climbing_log.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.climbing_log.enums.ClimbType;
import com.climbing_log.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

    @Query("SELECT l FROM Location l WHERE l.area = ?1 OR l.sector = ?1")
    public List<Location> findByName(String area); // Use Like insead of = ??

    @Query("SELECT DISTINCT l.sector FROM Location l INNER JOIN Climb c ON l.id = c.location.id WHERE c.type = ?1 AND l.sector != null")
    public List<String> findSectors(ClimbType climbType);

    @Query("SELECT DISTINCT l.area FROM Location l INNER JOIN Climb c ON l.id = c.location.id WHERE c.type = ?1 AND l.area != null")
    public List<String> findAreas(ClimbType climbType);

}
