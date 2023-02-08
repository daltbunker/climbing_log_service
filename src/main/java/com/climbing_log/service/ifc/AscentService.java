package com.climbing_log.service.ifc;

import java.util.List;

import com.climbing_log.model.Ascent;
import com.climbing_log.model.AscentClimb;

public interface AscentService {
    Ascent addAscent(Ascent newAscent);

    Ascent updateAscent(Ascent newAscent);

    Ascent getAscentById(Integer id);
    
    List<AscentClimb> getAllAscents();

    List<AscentClimb> getAscentsByUserId(Integer userId);
}
