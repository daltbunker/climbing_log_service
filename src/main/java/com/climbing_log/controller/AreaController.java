package com.climbing_log.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.climbing_log.model.Area;
import com.climbing_log.model.AreaClient;
import com.climbing_log.model.AreaPath;
import com.climbing_log.service.AreaService;

@RestController
@RequestMapping(path = "/api/area")
public class AreaController {
  @Autowired
  AreaService areaService;

  @GetMapping("")
  public ResponseEntity<List<AreaClient>> findAllAreasWithPath(
    @RequestParam(required=false, name="query") String query,
    @RequestParam(required=false, name="area_id") Integer areaId) {
      List<AreaPath> areas = areaService.findAllAreasWithPath(areaId, query + "%");
      List<AreaClient> areasForClient = areaService.createAreasForClient(areas);
      return ResponseEntity.status(HttpStatus.OK).body(areasForClient);
  }

  @GetMapping("/country")
  public ResponseEntity<List<Area>> getCountries() {
    List<Area> countries = areaService.getCountries();
    return ResponseEntity.status(HttpStatus.OK).body(countries);
  }

  @GetMapping("/{area_id}/children")
  public ResponseEntity<List<AreaClient>> getChildren(
    @PathVariable(name = "area_id") int areaId
  ) {
    List<Area> children = areaService.findChildren(areaId);
    List<AreaClient> childrenForClient = areaService.createChildrenForClient(children);
    return ResponseEntity.status(HttpStatus.OK).body(childrenForClient);
  }

  @GetMapping("/state")
  public ResponseEntity<List<Area>> getStates() {
    List<Area> states = areaService.getStates();
    return ResponseEntity.status(HttpStatus.OK).body(states);
  }
}
