package com.climbing_log.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.climbing_log.enums.ClimbType;
import com.climbing_log.enums.Grade;
import com.climbing_log.model.Climb;
import com.climbing_log.model.ClimbLocation;
import com.climbing_log.model.ClimbRequest;
import com.climbing_log.model.Location;
import com.climbing_log.service.ClimbService;
import com.climbing_log.service.LocationService;

@RestController
@RequestMapping(path = "/api/climbs")
public class ClimbController {
    @Autowired
    ClimbService climbService;
    @Autowired
    LocationService locationService;

    @PostMapping(path = "/add") 
    public ResponseEntity<Climb> addClimb(
            @RequestBody @Valid ClimbRequest newClimb) {
        Location location = new Location();
        location.setArea(newClimb.getArea());
        location.setCountry(newClimb.getCountry());
        location.setSector(newClimb.getSector());
        location.setState(newClimb.getState());

        Climb climb = new Climb();
        Grade grade = Grade.fromName(newClimb.getGrade());
        if (newClimb.getGrade().charAt(0) == 'V') {
            climb.setType(ClimbType.BOULDER);
        } else {
            climb.setType(ClimbType.ROUTE);
        }
        climb.setName(newClimb.getName());
        climb.setGrade(grade);

        Integer locationId = locationService.getLocationId(location);
        if (locationId > 0) {
            Location existingLocation = locationService.getLocationById(locationId);
            climb.setLocation(existingLocation);
        } else {
            Location newLocation = locationService.addLocation(location);
            climb.setLocation(newLocation);
        }

        Climb addedClimb = climbService.addClimb(climb);
        return ResponseEntity.ok(addedClimb);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Climb> getClimb(
            @PathVariable(name = "id") Integer id ) {
        Climb climb = climbService.getClimbById(id);
        return ResponseEntity.ok(climb);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<ClimbLocation>> getAllClimbs(
        @RequestParam(required = false, name = "name") String name,
        @RequestParam(required = false, name = "location_id") Integer locationId) {
            if (locationId != null) {
                List<ClimbLocation> climbs = climbService.getClimbsByLocation(locationId);
                return ResponseEntity.ok(climbs);
            } else if (name != null) {
                List<ClimbLocation> climbs = climbService.getClimbsByName(name);
                return ResponseEntity.ok(climbs);
            } else {
                List<ClimbLocation> climbs = climbService.getAllClimbs();
                return ResponseEntity.ok(climbs);
            }
    }
}
