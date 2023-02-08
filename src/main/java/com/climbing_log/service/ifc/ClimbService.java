package com.climbing_log.service.ifc;

import java.util.List;

import com.climbing_log.model.Climb;
import com.climbing_log.model.ClimbLocation;

public interface ClimbService {
    Climb addClimb(Climb newClimb);

    Climb getClimbById(Integer id);

    List<ClimbLocation> getAllClimbs();
    
    List<ClimbLocation> getClimbsByLocation(Integer id);

    List<ClimbLocation> getClimbsByName(String name);
}
