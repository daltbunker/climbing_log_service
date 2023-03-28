package com.climbing_log.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.climbing_log.model.Ascent;
import com.climbing_log.model.AscentClimb;
import com.climbing_log.model.Climb;
import com.climbing_log.model.User;
import com.climbing_log.service.AscentService;
import com.climbing_log.service.ClimbService;
import com.climbing_log.service.UserService;

@RestController
@RequestMapping(path = "/api/ascent")
public class AscentController {
    @Autowired
    AscentService ascentService;
    @Autowired
    ClimbService climbService;
    @Autowired
    UserService userService;

    @PostMapping(path = "")
    public ResponseEntity<Ascent> addAscent(
            @RequestBody @Valid Ascent newAscent,
            @RequestParam(required = true, name = "user") String username,
            @RequestParam(required = true, name = "climb" ) Integer climb_id) {
        Climb climb = climbService.getClimbById(climb_id);
        newAscent.setClimb(climb);
        User user = userService.getUserByUsername(username);
        newAscent.setUser(user);
        Ascent ascent = ascentService.addAscent(newAscent);
        return ResponseEntity.ok(ascent);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Ascent> updateAscent(
            @RequestBody @Valid Ascent updatedAscent,
            @PathVariable(name = "id") Integer id) {
        updatedAscent.setId(id);
        Ascent ascent = ascentService.updateAscent(updatedAscent);
        return ResponseEntity.ok(ascent);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Ascent> getAscent(
            @PathVariable(name = "id") Integer id ) {
        Ascent ascent = ascentService.getAscentById(id);
        return ResponseEntity.ok(ascent);
    }

    @GetMapping(path = "")
    public ResponseEntity<List<AscentClimb>> getAllAscents(
            @RequestParam(required = false, name = "user") String username) {
        if (username != null) {
            User user = userService.getUserByUsername(username);
            List<AscentClimb> ascents = ascentService.getAscentsByUserId(user.getId());
            return ResponseEntity.ok(ascents);
        }
        List<AscentClimb> ascents = ascentService.getAllAscents();
        return ResponseEntity.ok(ascents);
    }

}
