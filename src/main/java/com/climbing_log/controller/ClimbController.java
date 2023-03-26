package com.climbing_log.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.climbing_log.model.Area;
import com.climbing_log.model.Climb;
import com.climbing_log.model.ClimbRequest;
import com.climbing_log.service.AreaService;
import com.climbing_log.service.ClimbService;

@RestController
@RequestMapping(path = "/api/climbs")
public class ClimbController {
    @Autowired
    ClimbService climbService;
    @Autowired
    AreaService areaService;

    @PostMapping(path = "") 
    public ResponseEntity<Climb> addClimb(
            @RequestBody @Valid ClimbRequest newClimb) {
        Climb climb = new Climb();
        Grade grade = Grade.fromName(newClimb.getGrade());
        if (newClimb.getGrade().charAt(0) == 'V') {
            climb.setType(ClimbType.BOULDER);
        } else {
            climb.setType(ClimbType.ROUTE);
        }
        climb.setName(newClimb.getName());
        climb.setGrade(grade);

        if (newClimb.getCountryId() == null) {
            System.out.println("bad req");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        
        Area area = this.areaService.saveArea(newClimb.getPath(), newClimb.getCountryId());
        if (area == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        climb.setArea(area);
        Climb addedClimb = climbService.addClimb(climb);
        return ResponseEntity.ok(addedClimb);
        
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Climb> getClimb(
            @PathVariable(name = "id") Integer id ) {
        Climb climb = climbService.getClimbById(id);
        return ResponseEntity.ok(climb);
    }

    @GetMapping(path = "")
    public ResponseEntity<List<Climb>> findClimbsByAreaId(
        @RequestParam(required = true, name = "area_id") int areaId
    ) {
        return ResponseEntity.ok(climbService.findClimbsByAreaId(areaId));
    }
}
