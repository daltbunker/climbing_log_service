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

import com.climbing_log.model.Climb;
import com.climbing_log.model.ClimbLocation;
import com.climbing_log.model.Location;
import com.climbing_log.service.ifc.ClimbService;
import com.climbing_log.service.ifc.LocationService;

@RestController
@RequestMapping(path = "/api/climbs")
public class ClimbController {
    @Autowired
    ClimbService climbService;
    @Autowired
    LocationService locationService;

    @PostMapping(path = "/add/{location_id}") 
    public ResponseEntity<Climb> addClimb(
            @RequestBody @Valid Climb newClimb,
            @PathVariable(name = "location_id") Integer location_id) {

        Location location = locationService.getLocationById(location_id);
        newClimb.setLocation(location);
        Climb climb = climbService.addClimb(newClimb);
        return ResponseEntity.ok(climb);
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
