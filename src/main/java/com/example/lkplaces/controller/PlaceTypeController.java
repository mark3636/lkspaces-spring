package com.example.lkplaces.controller;

import com.example.lkplaces.jpa.entity.PlaceType;
import com.example.lkplaces.service.PlaceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/place-types")
public class PlaceTypeController {
    private final PlaceTypeService placeTypeService;

    @Autowired
    public PlaceTypeController(PlaceTypeService placeTypeService) {
        this.placeTypeService = placeTypeService;
    }

    @GetMapping
    public List<PlaceType> getAll() {
        return placeTypeService.getAll();
    }
}
