package com.climbing_log.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.climbing_log.exception.ResourceNotFoundException;
import com.climbing_log.model.Climb;
import com.climbing_log.model.ClimbClient;
import com.climbing_log.repository.ClimbRepository;

@Service
public class ClimbService {
    @Autowired
    private ClimbRepository climbRepository;

    public Climb addClimb(Climb newClimb) {
        Climb climb = climbRepository.save(newClimb);
        return climb;
    }

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

    public List<ClimbClient> findClimbsByAreaId(Integer id) {
        List<Climb> climbs = climbRepository.findClimbsByAreaId(id);
        return this.createClimbsForClient(climbs);
    }

    public Integer getCountByArea(Integer id) {
        return climbRepository.getCountByArea(id);
    }

    public List<String> findClimbsByQuery(String query) {
      return climbRepository.findClimbsByQuery(query);
    }

    public List<ClimbClient> findClimbsByName(String name) {
        List<Climb> climbs = climbRepository.findClimbsByName(name);
        return this.createClimbsForClient(climbs);
    }

    private List<ClimbClient> createClimbsForClient(List<Climb> climbs) {
        List<ClimbClient> climbsForClient = new ArrayList<>();
        for (Climb climb: climbs) {
            ClimbClient climbClient = new ClimbClient();
            climbClient.setId(climb.getId());
            climbClient.setName(climb.getName());
            climbClient.setGrade(climb.getGrade());
            climbClient.setArea(climb.getArea().getName());
            climbsForClient.add(climbClient);
        }
        return climbsForClient;
    }

    public void updateGrade(Integer climbId, String grade) {
        Optional<Climb> climbOpt = climbRepository.findById(climbId);
        if (climbOpt.isEmpty()) {
            throw new ResourceNotFoundException("climb not found");
        }
        Climb climb = climbOpt.get();
        climb.setGrade(grade);
        climbRepository.save(climb);
    }
}
