package com.example.lkplaces.service;

import com.example.lkplaces.jpa.entity.PlaceType;

import java.util.List;

public interface PlaceTypeService {
    List<PlaceType> getAll();

    PlaceType getById(Integer id);
}
