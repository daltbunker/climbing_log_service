package com.climbing_log.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.climbing_log.exception.ResourceNotFoundException;
import com.climbing_log.model.Ascent;
import com.climbing_log.model.AscentClimb;
import com.climbing_log.repository.AscentRepository;

@Service
public class AscentService{
    @Autowired
    AscentRepository ascentRepository;
    @Autowired
    ClimbService climbService;

    private String padLeft(String s, int n) {
        return String.format("%" + n + "s", s).replace(' ', '0');
    }

    private String getAverageGrade(List<String> grades) {
        int gradesSize = grades.size();
        if (gradesSize == 0) {
            return null;
        }
        int redSum = 0;
        int greenSum = 0;
        int blueSum = 0;
        for(String grade: grades) {
            if (grade != null) {
                redSum += Integer.parseInt(grade.substring(1, 3), 16);
                greenSum += Integer.parseInt(grade.substring(3, 5), 16);
                blueSum += Integer.parseInt(grade.substring(5, 7), 16);
            }
        }
        return "#" + padLeft(Integer.toHexString(redSum / gradesSize), 2)
                + padLeft(Integer.toHexString(greenSum / gradesSize), 2)
                + padLeft(Integer.toHexString(blueSum / gradesSize), 2);
    }

    public Ascent addAscent(Ascent newAscent) {
        Ascent ascent = ascentRepository.save(newAscent);
        climbService.updateGrade(
            newAscent.getClimb().getId(), 
            getAverageGrade(ascentRepository.getGradesByClimbId(newAscent.getClimb().getId()))
        );
        return ascent;
    }

    public Ascent updateAscent(Ascent updatedAscent) {
        Ascent prevAscent = this.getAscentById(updatedAscent.getId());
        prevAscent.setAttempts(updatedAscent.getAttempts());
        prevAscent.setComment(updatedAscent.getComment());
        prevAscent.setDate(updatedAscent.getDate());
        prevAscent.setGrade(updatedAscent.getGrade());
        prevAscent.setOver200lbs(updatedAscent.getOver200lbs());

        Ascent ascent = ascentRepository.save(prevAscent);

        climbService.updateGrade(
          ascent.getClimb().getId(), 
          getAverageGrade(ascentRepository.getGradesByClimbId(ascent.getClimb().getId()))
      );
        return ascent;
    }

    public Ascent getAscentById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ascent id must not be null");
        }
        Optional<Ascent> ascentOpt = ascentRepository.findById(id);

        if (ascentOpt.isEmpty()) {
            throw new ResourceNotFoundException("ascent not found");
        }

        Ascent ascent = ascentOpt.get();
        return ascent;
    }

    public List<AscentClimb> getAllAscents() {
        List<Ascent> ascents = ascentRepository.findAll();
        if (ascents == null || ascents.isEmpty()) {
            throw new ResourceNotFoundException("ascents not found");
        }
        List<AscentClimb> ascentResponses = this.createAscentClimb(ascents); 
        return ascentResponses;
    }

    public List<AscentClimb> getAscentsByUserId(Integer userId) {
        List<Ascent> ascents = ascentRepository.findAllByUserId(userId);
        List<AscentClimb> ascentResponses = this.createAscentClimb(ascents); 
        return ascentResponses;
    }

    private List<AscentClimb> createAscentClimb(List<Ascent> ascents) {
        List<AscentClimb> ascentResponses = new ArrayList<AscentClimb>();
        for (Ascent ascent: ascents) {
            AscentClimb ascentResponse = new AscentClimb();
            ascentResponse.setId(ascent.getId());
            ascentResponse.setName(ascent.getClimb().getName());
            ascentResponse.setAttempts(ascent.getAttempts());
            ascentResponse.setComment(ascent.getComment());
            ascentResponse.setDate(ascent.getDate());
            ascentResponse.setGrade(ascent.getGrade());
            ascentResponse.setOver200lbs(ascent.getOver200lbs());

            ascentResponses.add(ascentResponse);
        }
        return ascentResponses; 
    } 

}
