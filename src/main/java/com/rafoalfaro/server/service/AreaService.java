package com.rafoalfaro.server.service;

import com.rafoalfaro.server.domain.Area;
import com.rafoalfaro.server.domain.Position;
import com.rafoalfaro.server.repository.AreaRepository;
import com.rafoalfaro.server.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaService {
    @Autowired private AreaRepository areaRepository;
    @Autowired private PositionRepository positionRepository;
    public List<Area> getAllAreas(){
        return areaRepository.findAll();
    }

    public List<Position> getAllPositionByArea(Long areaId){
        return positionRepository.getPositionByAreaId(areaId);
    }
}
