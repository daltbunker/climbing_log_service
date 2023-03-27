package com.climbing_log.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.climbing_log.exception.ResourceNotFoundException;
import com.climbing_log.model.Area;
import com.climbing_log.model.AreaClient;
import com.climbing_log.model.AreaPath;
import com.climbing_log.model.Path;
import com.climbing_log.repository.AreaRepository;

@Service
public class AreaService {
  @Autowired
  AreaRepository areaRepository;
  @Autowired
  ClimbService climbService;

  public Area findById(Integer id) {
    if (id == null) {
      throw new IllegalArgumentException("area id must not be null");
    }
    Optional<Area> areaOpt = areaRepository.findById(id);
    if(areaOpt.isEmpty()) {
      throw new ResourceNotFoundException("area not found");
    }
    return areaOpt.get();
  }

  public Area findByName(String name) {
    Area area = areaRepository.findByName(name);
    return area;
  }

  public List<Area> getCountries() {
    List<Area> countries = areaRepository.getCountries();
    return countries;
  }

  public List<Area> findChildren(int parentId) {
    List<Area> children = areaRepository.findChildren(parentId);
    return children;
  }
   
  public List<Area> getStates() {
    List<Area> countries = areaRepository.getStates();
    return countries;
  } 

  public List<AreaPath> findAllAreasWithPath(Integer countryId, String query) {
    List<AreaPath> areas = areaRepository.findAllAreasWithPath(countryId == null ? 0 : 1, countryId, query);
    List<AreaPath> areasWithData = new ArrayList<>();
    for (AreaPath area: areas) {
      Integer childCount = this.getChildrenCount(area.getId());
      Integer climbCount = climbService.getCountByArea(area.getId());
      if (childCount > 0 || climbCount > 0) {
        areasWithData.add(area);
      }
    }
    return areasWithData;
  }

  public Area saveArea(List<Path> path, Integer countryId) {
    if (path.isEmpty()) {
      throw new IllegalArgumentException("path must not be empty");
    }
    Area parentArea = this.findById(countryId);
    Area currentArea = null;
    for(Path area: path) {
      currentArea = area.getId() > 0 ? this.findById(area.getId()) : null;
      if (currentArea == null) {
        Area newArea = new Area();
        newArea.setName(area.getName());
        newArea.setArea(parentArea);
        currentArea = areaRepository.save(newArea);
      } 
      parentArea = currentArea;
    }
    return currentArea;
  }

  private List<Path> createPath(String names, String ids) {
    List<String> pathNames = Arrays.asList(names.split("\\s*>\\s*"));
    List<String> pathIds = Arrays.asList(ids.split("\\s*,\\s*"));
    List<Path> path = new ArrayList<>();
    for(int i = 0; i < pathNames.size(); i++) {
      Path pathValue = new Path();
      pathValue.setId(Integer.valueOf(pathIds.get(i)));
      pathValue.setName(pathNames.get(i)); 
      path.add(pathValue);
    }
    return path;
  }

  public List<AreaClient> createAreasForClient(List<AreaPath> areas) {
    List<AreaClient> areasForClient = new ArrayList<>();
    for (AreaPath area: areas) {
      AreaClient areaClient = new AreaClient();
      areaClient.setId(area.getId());
      areaClient.setName(area.getName());
      areaClient.setClimbCount(climbService.getCountByArea(area.getId()));
      areaClient.setChildrenCount(this.getChildrenCount(area.getId()));
      areaClient.setPath(this.createPath(area.getPath(), area.getPathIds()));
      areasForClient.add(areaClient);
    }
    return areasForClient;
  }

  public List<AreaClient> createChildrenForClient(List<Area> children) {
    List<AreaClient> childrenForClient = new ArrayList<>();
    for (Area area: children) {
      AreaClient areaClient = new AreaClient();
      areaClient.setId(area.getId());
      areaClient.setName(area.getName());
      areaClient.setClimbCount(climbService.getCountByArea(area.getId()));
      areaClient.setChildrenCount(this.getChildrenCount(area.getId()));
      childrenForClient.add(areaClient);
    }
    return childrenForClient;
  }

  public Integer getChildrenCount(Integer id) {
      return areaRepository.getChildrenCount(id);
  }
}
