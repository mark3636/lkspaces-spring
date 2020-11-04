package com.example.lkplaces.service;

import com.example.lkplaces.jpa.entity.MapMarker;
import com.example.lkplaces.jpa.enums.EnumStatus;

import java.util.List;

public interface MapMarkerService {
    MapMarker add(MapMarker marker);

    MapMarker update(MapMarker marker);

    void delete(Integer id);

    MapMarker getById(Integer id);

    List<MapMarker> getAll();

    MapMarker changeStatus(Integer id, EnumStatus status);
}
