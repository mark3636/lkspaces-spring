package com.example.lkplaces.jpa.repository;

import com.example.lkplaces.jpa.entity.MapMarker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapMarkerRepository extends JpaRepository<MapMarker, Integer> {
}
