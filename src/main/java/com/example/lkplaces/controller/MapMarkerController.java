package com.example.lkplaces.controller;

import com.example.lkplaces.jpa.entity.MapMarker;
import com.example.lkplaces.jpa.enums.EnumStatus;
import com.example.lkplaces.service.MapMarkerService;
import com.example.lkplaces.web.dto.MapMarkerDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/map-markers")
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
    public MapMarker add(@RequestParam String mapMarker, @RequestParam(required = false) MultipartFile image) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return mapMarkerService.add(objectMapper.readValue(mapMarker, MapMarkerDto.class), image);
    }

    @PutMapping("/{id}")
    public MapMarker update(@RequestParam String mapMarker, @RequestParam(required = false) MultipartFile image) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return mapMarkerService.update(objectMapper.readValue(mapMarker, MapMarkerDto.class), image);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        mapMarkerService.delete(id);
    }

    @PostMapping("/{id}")
    public MapMarker changeStatus(@PathVariable Integer id, @RequestParam("status") EnumStatus status) {
        return mapMarkerService.changeStatus(id, status);
    }
}
