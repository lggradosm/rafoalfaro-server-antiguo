package com.rafoalfaro.server.controller;

import com.rafoalfaro.server.domain.Area;
import com.rafoalfaro.server.domain.Position;
import com.rafoalfaro.server.service.AreaService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/area")
public class AreaController {
    @Autowired private AreaService areaService;

    @GetMapping(value = "")
    public ResponseEntity<List<Area>> getAreas(){
        return ResponseEntity.ok(areaService.getAllAreas());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<Position>> getPositionsByArea(@PathVariable Long id){
        return ResponseEntity.ok(areaService.getAllPositionByArea(id));
    }
}
