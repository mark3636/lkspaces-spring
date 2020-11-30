package com.example.lkplaces.service.impl;

import com.example.lkplaces.jpa.entity.MapMarker;
import com.example.lkplaces.jpa.entity.PlaceType;
import com.example.lkplaces.jpa.enums.EnumStatus;
import com.example.lkplaces.jpa.repository.MapMarkerRepository;
import com.example.lkplaces.service.MapMarkerService;
import com.example.lkplaces.service.PlaceTypeService;
import com.example.lkplaces.web.dto.MapMarkerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapMarkerServiceImpl implements MapMarkerService {
    private final MapMarkerRepository mapMarkerRepository;
    private final PlaceTypeService placeTypeService;

    @Autowired
    public MapMarkerServiceImpl(MapMarkerRepository mapMarkerRepository,
                                PlaceTypeService placeTypeService) {
        this.mapMarkerRepository = mapMarkerRepository;
        this.placeTypeService = placeTypeService;
    }

    @Override
    public MapMarker add(MapMarkerDto marker) {
        PlaceType placeType = placeTypeService.getById(marker.getPlaceTypeId());
        MapMarker newMarker = MapMarker.builder()
                .label(marker.getLabel())
                .description(marker.getDescription())
                .lat(marker.getLat())
                .lng(marker.getLng())
                .placeType(placeType)
                .status(EnumStatus.WAITING_FOR_APPROVAL)
                .build();
        return mapMarkerRepository.save(newMarker);
    }

    @Override
    public MapMarker update(MapMarkerDto marker) {
        PlaceType placeType = placeTypeService.getById(marker.getPlaceTypeId());
        MapMarker newMarker = MapMarker.builder()
                .id(marker.getId())
                .label(marker.getLabel())
                .description(marker.getDescription())
                .lat(marker.getLat())
                .lng(marker.getLng())
                .placeType(placeType)
                .build();
        return mapMarkerRepository.save(newMarker);
    }

    @Override
    public void delete(Integer id) {
        mapMarkerRepository.deleteById(id);
    }

    @Override
    public MapMarker getById(Integer id) {
        return mapMarkerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Маркер не найден"));
    }

    @Override
    public List<MapMarker> getAll() {
        return mapMarkerRepository.findAll();
    }

    @Override
    public MapMarker changeStatus(Integer id, EnumStatus status) {
        MapMarker mapMarker = mapMarkerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Маркер не найден"));
        mapMarker.setStatus(status);
        return mapMarkerRepository.save(mapMarker);
    }
}
