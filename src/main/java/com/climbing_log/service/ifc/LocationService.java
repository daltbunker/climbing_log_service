package com.climbing_log.service.ifc;

import java.util.List;

import com.climbing_log.enums.ClimbType;
import com.climbing_log.model.Location;

public interface LocationService {
    Location addLocation(Location newLocation);

    Location getLocationById(Integer id);

    List<Location> getLocationByName(String sector);

    List<String> getSectors(ClimbType climbType);
    
    List<String> getAreas(ClimbType climbType);
}
    