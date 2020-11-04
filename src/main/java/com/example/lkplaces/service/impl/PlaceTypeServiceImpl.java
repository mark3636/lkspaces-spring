package com.example.lkplaces.service.impl;

import com.example.lkplaces.jpa.entity.PlaceType;
import com.example.lkplaces.jpa.repository.PlaceTypeRepository;
import com.example.lkplaces.service.PlaceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceTypeServiceImpl implements PlaceTypeService {
    private final PlaceTypeRepository placeTypeRepository;

    @Autowired
    public PlaceTypeServiceImpl(PlaceTypeRepository placeTypeRepository) {
        this.placeTypeRepository = placeTypeRepository;
    }

    @Override
    public List<PlaceType> getAll() {
        return placeTypeRepository.findAll();
    }
}
