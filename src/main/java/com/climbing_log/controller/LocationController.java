package com.climbing_log.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.climbing_log.enums.ClimbType;
import com.climbing_log.model.AllLocations;
import com.climbing_log.model.Location;
import com.climbing_log.service.ifc.LocationService;

@RestController
@RequestMapping(path = "/api/locations")
public class LocationController {
    @Autowired
    LocationService locationService;

    @PostMapping(path = "/add")
    public ResponseEntity<Location> addLocation(
            @RequestBody @Valid Location newLocation) {
        Location location = locationService.addLocation(newLocation);
        return ResponseEntity.ok(location);
    }

    @GetMapping(path = "")
    public ResponseEntity<Location> getLocation(
            @RequestParam(required = false, name = "id") Integer id) {
        if (id != null) {
            Location location = locationService.getLocationById(id);
            return ResponseEntity.ok(location);
        }
        throw new IllegalArgumentException("location must have id");
    }

    @GetMapping(path = "/search")
    public ResponseEntity<List<Location>> searchLocations(
            @RequestParam(required = false, name = "name") String name) {
        if (name != null) {
            List<Location> locations = locationService.getLocationByName(name);
            return ResponseEntity.ok(locations);
        }
        throw new IllegalArgumentException("location search must have sector or area");
    }

    @GetMapping(path = "/all")
    public ResponseEntity<AllLocations> getAll(
            @RequestParam(required = true, name = "type") String type) {
        if (type.equals("routes")) {
            AllLocations locations = new AllLocations();
            List<String> sectors = locationService.getSectors(ClimbType.ROUTE);
            List<String> areas = locationService.getAreas(ClimbType.ROUTE);
            locations.setSectors(sectors);
            locations.setAreas(areas);
            return ResponseEntity.ok(locations);
        } else if (type.equals("boulders")) {
            AllLocations locations = new AllLocations();
            List<String> sectors = locationService.getSectors(ClimbType.BOULDER);
            List<String> areas = locationService.getAreas(ClimbType.BOULDER);
            locations.setSectors(sectors);
            locations.setAreas(areas);
            return ResponseEntity.ok(locations);
        }
        throw new IllegalArgumentException("get all locations must have type 'routes' or 'boulders'");
    }

}
