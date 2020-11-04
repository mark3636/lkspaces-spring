package com.example.lkplaces.service.impl;

import com.example.lkplaces.jpa.entity.MapMarker;
import com.example.lkplaces.jpa.enums.EnumStatus;
import com.example.lkplaces.jpa.repository.MapMarkerRepository;
import com.example.lkplaces.service.MapMarkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapMarkerServiceImpl implements MapMarkerService {
    private final MapMarkerRepository mapMarkerRepository;

    @Autowired
    public MapMarkerServiceImpl(MapMarkerRepository mapMarkerRepository) {
        this.mapMarkerRepository = mapMarkerRepository;
    }

    @Override
    public MapMarker add(MapMarker marker) {
        marker.setStatus(EnumStatus.WAITING_FOR_APPROVAL);
        return mapMarkerRepository.save(marker);
    }

    @Override
    public MapMarker update(MapMarker marker) {
        return mapMarkerRepository.save(marker);
    }

    @Override
    public void delete(Integer id) {
        mapMarkerRepository.deleteById(id);
    }

    @Override
    public MapMarker getById(Integer id) {
        return mapMarkerRepository.findById(id).orElse(null);
    }

    @Override
    public List<MapMarker> getAll() {
        return mapMarkerRepository.findAll();
    }

    @Override
    public MapMarker changeStatus(Integer id, EnumStatus status) {
        MapMarker mapMarker = mapMarkerRepository.findById(id).orElseThrow(() -> new RuntimeException(""));
        mapMarker.setStatus(status);
        return mapMarkerRepository.save(mapMarker);
    }
}
