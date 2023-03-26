package com.climbing_log.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.climbing_log.enums.ClimbType;
import com.climbing_log.exception.ResourceNotFoundException;
import com.climbing_log.model.Climb;
import com.climbing_log.repository.ClimbRepository;

@Service
public class ClimbService {
    @Autowired
    private ClimbRepository climbRepository;

    public Climb addClimb(Climb newClimb) {
        if (newClimb.getGrade().isRoute() && newClimb.getType() == ClimbType.BOULDER) {
	        throw new IllegalArgumentException("climbe type and grade don't match");
	    }

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

    public List<Climb> findClimbsByAreaId(Integer id) {
        List<Climb> climbs = climbRepository.findClimbsByAreaId(id);
        return climbs;
    }

    public Integer getCountByArea(Integer id) {
        return climbRepository.getCountByArea(id);
    }
}
