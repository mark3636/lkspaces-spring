package com.example.lkplaces.controller;

import com.example.lkplaces.jpa.entity.MapMarker;
import com.example.lkplaces.service.MapMarkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/map-markers")
public class MapMarkerController {
    private final MapMarkerService mapMarkerService;

    @Autowired
    public MapMarkerController(MapMarkerService mapMarkerService) {
        this.mapMarkerService = mapMarkerService;
    }

    @GetMapping
    public List<MapMarker> getAll() {
        return mapMarkerService.getAll();
    }

    @GetMapping("/{id}")
    public MapMarker getById(@PathVariable Integer id) {
        return mapMarkerService.getById(id);
    }

    @PostMapping
    public MapMarker add(@RequestBody MapMarker mapMarker) {
        return mapMarkerService.add(mapMarker);
    }

    @PutMapping("/{id}")
    public MapMarker update(@RequestBody MapMarker mapMarker) {
        return mapMarkerService.update(mapMarker);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        mapMarkerService.delete(id);
    }
}
