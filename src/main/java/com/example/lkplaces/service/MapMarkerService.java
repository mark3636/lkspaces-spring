package com.example.lkplaces.service;

import com.example.lkplaces.jpa.entity.MapMarker;
import com.example.lkplaces.jpa.enums.EnumStatus;
import com.example.lkplaces.web.dto.MapMarkerDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MapMarkerService {
    MapMarker add(MapMarkerDto marker, MultipartFile image);

    MapMarker update(MapMarkerDto marker, MultipartFile image);

    void delete(Integer id);

    MapMarker getById(Integer id);

    List<MapMarker> getAll();

    MapMarker changeStatus(Integer id, EnumStatus status);
}
