package com.example.lkplaces.jpa.repository;

import com.example.lkplaces.jpa.entity.PlaceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceTypeRepository extends JpaRepository<PlaceType, Integer> {
}
