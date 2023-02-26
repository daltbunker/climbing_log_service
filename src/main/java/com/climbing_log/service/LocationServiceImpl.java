package com.climbing_log.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.climbing_log.enums.ClimbType;
import com.climbing_log.exception.ResourceNotFoundException;
import com.climbing_log.model.Location;
import com.climbing_log.repository.LocationRepository;
import com.climbing_log.service.ifc.LocationService;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationRepository locationRepository;

    @Override
    public Location addLocation(Location newLocation) {
        Location location = locationRepository.save(newLocation);
        return location;
    }

    @Override
    public Location getLocationById(Integer id) {
        Optional<Location> locationOpt = locationRepository.findById(id);

        if (locationOpt.isEmpty()) {
            throw new ResourceNotFoundException("location not found by id");
        }
        
        Location location = locationOpt.get();
        return location;
    }

    @Override
    public List<Location> getLocationByName(String name) {
        List<Location> locationList = locationRepository.findByName(name);

        if (locationList == null || locationList.isEmpty()) {
            throw new ResourceNotFoundException("location not found by name");
        }
        
        return locationList;
    }

    @Override
    public List<String> getSectors(ClimbType climbType) {
        List<String> sectors = locationRepository.findSectors(climbType);
        if (sectors == null || sectors.isEmpty()) {
            throw new ResourceNotFoundException("sectors not found");
        }
        return sectors;
    }

    @Override
    public List<String> getAreas(ClimbType climbType) {
        List<String> areas = locationRepository.findAreas(climbType);
        if (areas == null || areas.isEmpty()) {
            throw new ResourceNotFoundException("areas not found");
        }
        return areas;
    }

    @Override
    public Integer getLocationId(Location location) {
        Integer locationId = locationRepository.findLocationId(location);
        return locationId != null ? locationId : -1;
    }
}
