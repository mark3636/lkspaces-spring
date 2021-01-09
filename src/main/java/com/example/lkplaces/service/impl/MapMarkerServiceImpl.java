package com.example.lkplaces.service.impl;

import com.example.lkplaces.jpa.entity.MapMarker;
import com.example.lkplaces.jpa.entity.PlaceType;
import com.example.lkplaces.jpa.enums.EnumActionType;
import com.example.lkplaces.jpa.enums.EnumDomainType;
import com.example.lkplaces.jpa.enums.EnumStatus;
import com.example.lkplaces.jpa.repository.MapMarkerRepository;
import com.example.lkplaces.service.AuditService;
import com.example.lkplaces.service.MapMarkerService;
import com.example.lkplaces.service.PlaceTypeService;
import com.example.lkplaces.web.dto.MapMarkerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.ImageUtils;

import java.util.List;

@Service
public class MapMarkerServiceImpl implements MapMarkerService {
    private final MapMarkerRepository mapMarkerRepository;
    private final PlaceTypeService placeTypeService;
    private final AuditService auditService;

    @Autowired
    public MapMarkerServiceImpl(MapMarkerRepository mapMarkerRepository,
                                PlaceTypeService placeTypeService,
                                AuditService auditService) {
        this.mapMarkerRepository = mapMarkerRepository;
        this.placeTypeService = placeTypeService;
        this.auditService = auditService;
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
                .image(ImageUtils.compressBytes(marker.getImage()))
                .build();
        newMarker = mapMarkerRepository.save(newMarker);
        auditService.audit(EnumActionType.CREATE, EnumDomainType.MAP_MARKER);
        newMarker.setImage(ImageUtils.decompressBytes(newMarker.getImage()));
        return newMarker;
    }

    @Override
    public MapMarker update(MapMarkerDto marker) {
        PlaceType placeType = placeTypeService.getById(marker.getPlaceTypeId());
        MapMarker oldMarker = getById(marker.getId());
        oldMarker.setLabel(marker.getLabel());
        oldMarker.setDescription(marker.getDescription());
        oldMarker.setLat(marker.getLat());
        oldMarker.setLng(marker.getLng());
        oldMarker.setPlaceType(placeType);
        oldMarker.setImage(ImageUtils.compressBytes(marker.getImage()));
        oldMarker = mapMarkerRepository.save(oldMarker);
        auditService.audit(EnumActionType.UPDATE, EnumDomainType.MAP_MARKER);
        oldMarker.setImage(ImageUtils.decompressBytes(oldMarker.getImage()));
        return oldMarker;
    }

    @Override
    public void delete(Integer id) {
        mapMarkerRepository.deleteById(id);
        auditService.audit(EnumActionType.DELETE, EnumDomainType.MAP_MARKER);
    }

    @Override
    public MapMarker getById(Integer id) {
        return mapMarkerRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Маркер не найден"));
    }

    @Override
    public List<MapMarker> getAll() {
        List<MapMarker> mapMarkers = mapMarkerRepository.findAll();
        mapMarkers.forEach(mapMarker -> mapMarker.setImage(ImageUtils.decompressBytes(mapMarker.getImage())));
        return mapMarkers;
    }

    @Override
    public MapMarker changeStatus(Integer id, EnumStatus status) {
        MapMarker mapMarker = getById(id);
        mapMarker.setStatus(status);
        mapMarker = mapMarkerRepository.save(mapMarker);
        auditService.audit(EnumStatus.APPROVED.equals(status) ? EnumActionType.APPROVE : EnumActionType.REJECT,
                EnumDomainType.MAP_MARKER);
        mapMarker.setImage(ImageUtils.decompressBytes(mapMarker.getImage()));
        return mapMarker;
    }
}
