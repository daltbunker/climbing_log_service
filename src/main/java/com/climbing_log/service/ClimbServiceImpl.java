package com.climbing_log.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.climbing_log.enums.ClimbType;
import com.climbing_log.exception.ResourceNotFoundException;
import com.climbing_log.model.Climb;
import com.climbing_log.model.ClimbLocation;
import com.climbing_log.repository.ClimbRepository;
import com.climbing_log.service.ifc.ClimbService;

@Service
public class ClimbServiceImpl implements ClimbService {
    @Autowired
    private ClimbRepository climbRepository;

    @Override
    public Climb addClimb(Climb newClimb) {
        if (newClimb.getGrade().isRoute() && newClimb.getType() == ClimbType.BOULDER) {
	        throw new IllegalArgumentException("climbe type and grade don't match");
	    }

        Climb climb = climbRepository.save(newClimb);
        return climb;
    }

    @Override
    public Climb getClimbById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("climb id must not be null");
        }
        Optional<Climb> climbOpt = climbRepository.findById(id);

        if (climbOpt.isEmpty()) {
            throw new ResourceNotFoundException("climb not found");
        }

        Climb climb = climbOpt.get();
        return climb;
    }

    @Override
    public List<ClimbLocation> getAllClimbs() {
        List<Climb> climbs = climbRepository.findAll();
        if (climbs == null || climbs.isEmpty()) {
            throw new ResourceNotFoundException("climbs not found");
        }
        List<ClimbLocation> climbLocations = this.getClimbLocations(climbs);
        return climbLocations;
    }

    @Override
    public List<ClimbLocation> getClimbsByLocation(Integer id) {
        List<Climb> climbs = climbRepository.findClimbsByLocation(id);
        if (climbs == null || climbs.isEmpty()) {
            throw new ResourceNotFoundException("climbs not found");
        }
        List<ClimbLocation> climbLocations = this.getClimbLocations(climbs);
        return climbLocations;
    }

    @Override
    public List<ClimbLocation> getClimbsByName(String name) {
        List<Climb> climbs = climbRepository.findClimbsByName(name);
        if (climbs == null || climbs.isEmpty()) {
            throw new ResourceNotFoundException("climbs not found");
        }
        List<ClimbLocation> climbLocations = this.getClimbLocations(climbs);
        return climbLocations;
    }

    private List<ClimbLocation> getClimbLocations(List<Climb> climbs) {
        List<ClimbLocation> climbLocations = new ArrayList<>();
        for(Climb climb: climbs) {
            ClimbLocation climbLocation = new ClimbLocation();
            climbLocation.setId(climb.getId());
            climbLocation.setName(climb.getName());
            climbLocation.setType(climb.getType().toString());
            climbLocation.setGrade(climb.getGrade().toString());
            climbLocation.setArea(climb.getLocation().getArea());
            climbLocation.setSector(climb.getLocation().getSector());
            climbLocations.add(climbLocation);
        }
        return climbLocations;
    }
}
